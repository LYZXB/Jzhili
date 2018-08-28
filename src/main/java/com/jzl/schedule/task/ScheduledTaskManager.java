package com.jzl.schedule.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.jzl.common.ScheduledTask;
import com.jzl.schedule.queue.SMSQueueProducer;
import com.jzl.schedule.queue.UpdateBirthdayQueueProducer;
import com.jzl.utils.PropertiesUtils;

/**
 * 
 * @ClassName: ScheduledTaskManager
 * @Description: 定时任务管理器
 * @author Sombra
 * @date 2018年3月27日 下午6:06:09
 * @version V1.0
 */
@Component
public class ScheduledTaskManager {
	private Logger LOGGER = Logger.getLogger(ScheduledTaskManager.class);

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private UpdateBirthdayQueueProducer updateBirthdayProducer;
	@Autowired
	private SMSQueueProducer smsQueueProducer;

	/** 任务列表 */
	private static Map<String, ScheduledFuture<?>> jobs = new HashMap<>();
	private final static int POOL_SIZE = 64;
	private final static ConcurrentTaskScheduler ct = new ConcurrentTaskScheduler(
			Executors.newScheduledThreadPool(POOL_SIZE));

	/**
	 * @Title: index
	 * @Description: 暂时默认加载作业(根据job配置)
	 */
	@PostConstruct
	private void index() {
		Properties properties = PropertiesUtils.loadProperties("jobs.properties");
		String jobs = properties.getProperty("jobs");
		String[] jobz = jobs.split(",");
		for (String job : jobz) {
			// 启动自动任务
			ScheduledTask task = (ScheduledTask) applicationContext.getBean(job);
			task.setTaskid(job);
			task.setTigger(properties.getProperty(job + ".tigger"));
			try {
				ScheduledTaskManager.start(task);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.info("自动任务" + job + "调度失败", e);
			}
			// 初始化多线程处理类(阻塞队列)
			if (job.equals("autoUpdateBirthday")) {
				updateBirthdayProducer.start(5);
			}
			if (job.equals("autoPushShotMessage")) {
				smsQueueProducer.start(5);
			}
		}
	}

	/**
	 * 
	 * @Title: start
	 * @Description: 定时任务开启
	 * @param runable
	 *            void 返回类型
	 * @throws Exception
	 */
	public static void start(ScheduledTask task) throws Exception {
		if (task == null) {
			throw new Exception("task is null");
		}
		if (StringUtils.isBlank(task.getTaskid())) {
			throw new Exception("任务id不能为空!");
		}
		if (StringUtils.isBlank(task.getTigger())) {
			throw new Exception("cron时间表达式不能为空!");
		}

		try {
			// 1. 配置定时任务
			ScheduledFuture<?> scheduledTash = ct.schedule(task, new CronTrigger(task.getTigger()));

			// 2. 定时任务加入任务列表
			jobs.put(task.getTaskid(), scheduledTash);
		} catch (Exception e) {
			throw new Exception("系统错误", e);
		}
	}

	/**
	 * 
	 * @Title: stop
	 * @Description: 定时任务停止
	 * @throws Exception
	 */
	public static void stop(String job) throws Exception {
		// 1. 判断是否存在任务列表中
		ScheduledFuture<?> scheduledTask = jobs.get("job");
		if (scheduledTask == null) {
			throw new Exception("任务不存在任务列表中");
		}

		try {
			// 2. 如果存在则停止任务
			jobs.remove(job);
			if (!scheduledTask.isCancelled()) {
				/** false 表示当前任务若正在执行，则待其执行结束，再结束此任务. */
				scheduledTask.cancel(false);
			}
		} catch (Exception e) {
			throw new Exception("任务停止失败!", e);
		}
	}
}
