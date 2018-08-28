package com.jzl.schedule.queue;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzl.beans.birthday.Birthday;

/**
 * 
 * @ClassName: UpdateBirthdayQueueProducer  
 * @Description: 更新生日队列生产者  
 * @author Sombra  
 * @date 2018年3月28日 下午3:55:25  
 * @version V1.0
 */
@Component
public class UpdateBirthdayQueueProducer {
	private Logger LOGGER = Logger.getLogger(UpdateBirthdayQueueProducer.class);
	
	@Autowired
	private UpdateBirthdayQueueConsumer consumer;
	private BlockingQueue<List<Birthday>> queue = new LinkedBlockingQueue<>();
	private ExecutorService pool = null;
	private int maxThread = 5;
	
	/**
	 * 
	 * @Title: start  
	 * @Description: 初始化更新生日处理  
	 * @param maxThread  void    返回类型 
	 */
	public synchronized void start(int maxThread) {
		LOGGER.info("自动更新生日数据启动");
		
		// 0. 保证只初始化一次
		if (pool != null) {
			stop();
		}
		// 1. 把队列加入到消费者队列
		consumer.setQueue(queue);
		
		// 2. 初始化消费者线程
		this.maxThread = maxThread;
		pool = Executors.newFixedThreadPool(this.maxThread);
		for (int i = 0; i < maxThread; i++) {
			pool.execute(consumer);
		}
	}
	
	/**
	 * Servlet销毁自动执行
	 */
	@PreDestroy
	public synchronized void stop(){
		try {
			Thread.sleep(3000);
			queue.clear();
		} catch (InterruptedException e) {
			LOGGER.info("线程关闭异常",e);
		}
		if(pool != null){
			LOGGER.info("进行关闭线程池...");
			pool.shutdownNow();
			pool = null;
		}
	}
	
	public boolean addQueue(List<Birthday> birthdayList) {
		try {
			queue.add(birthdayList);
			LOGGER.info("添加确认订单对象至队列， 队列大小：" + queue.size());
			return true;
		} catch (Exception e) {
			LOGGER.error("添加任务异常:",e);
			return false;
		}
	}
}
