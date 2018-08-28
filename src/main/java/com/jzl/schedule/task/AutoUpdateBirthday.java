package com.jzl.schedule.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzl.beans.birthday.Birthday;
import com.jzl.common.ScheduledTask;
import com.jzl.enums.ScheduledTaskStatusEnum;
import com.jzl.schedule.queue.UpdateBirthdayQueueProducer;
import com.jzl.service.BirthdayService;
import com.jzl.utils.JedisUtils;
import com.jzl.utils.RedisDataRulerUtils;

/**
 * 
 * @ClassName: AutoUpdateBirthday  
 * @Description: 自动修改生日数据  
 * @author Sombra  
 * @date 2018年3月27日 下午9:24:01  
 * @version V1.0
 */
@Component
public class AutoUpdateBirthday extends ScheduledTask {
	private Logger LOGGER = Logger.getLogger(AutoUpdateBirthday.class);
	
	@Autowired
	UpdateBirthdayQueueProducer producer;
	@Autowired
	BirthdayService birthdayService;
	
	private int count = 1000;
	private String taskId = "autoUpdateBirthday";
	
	@Override
	protected void exec() {
		// 0. 判断是否需要执行任务
		if(isExec()) {
			return;
		}
		
		try {
			// 1. 获取需要更新的数据
			List<Birthday> birthdayList = birthdayService.findAllNeedUpdateBirthday(count);
			
			// 2. 添加到生产者队列
			producer.addQueue(birthdayList);
			LOGGER.info("待更新数据添加到生产者队列");
			

		} catch (Exception e) {
			LOGGER.info("执行自动任务出错", e);
		} finally {
			// 更新执行状态为未执行
			JedisUtils.set(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId), ScheduledTaskStatusEnum.NOT_EXEC.getCode());
		}
	}
	
	/**
	 * 
	 * @Title: isExec  
	 * @Description: 判断自动任务是否在执行状态  
	 * @return  boolean    返回类型 
	 */
	private boolean isExec() {
		String status = JedisUtils.get(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId));
		if (ScheduledTaskStatusEnum.isExec(status)) {
			return true;
		} 
		// 更新执行状态为执行中
		JedisUtils.set(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId), ScheduledTaskStatusEnum.IN_EXEC.getCode());
		return false;
	}
}
