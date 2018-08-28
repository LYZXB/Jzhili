package com.jzl.schedule.queue;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzl.beans.common.SMSTemplateParam;

/**
 * 
 * @ClassName: SMSQueueProducer  
 * @Description: 短信服务队列提供者  
 * @author Sombra  
 * @date 2018年3月30日 下午6:01:59  
 * @version V1.0
 */
@Component
public class SMSQueueProducer {
	private Logger LOGGER = Logger.getLogger(SMSQueueProducer.class);
	
	@Autowired
	private SMSQueueConsumer consumer;
	/** 当天要发出去的队列 */
	private BlockingQueue<SMSTemplateParam> queue = new LinkedBlockingQueue<>(); 
	private ExecutorService pool = null;
	private int maxCount = 5;
	
	/**
	 * 其他地方只需要调用这个方法，就行了
	 * @Title: setAllQueue  
	 * @Description: 数据推入队列  
	 * @param smsQueue  void    返回类型 
	 */
	public void addQueue(Queue<SMSTemplateParam> smsQueue) {
		try {
			queue.addAll(smsQueue);
			LOGGER.info("数据加入队列成功,队列大小：" + queue.size());
		} catch (Exception e) {
			LOGGER.info("数据加入队列异常", e);
		}
	}
	
	/**
	 * @Title: start  
	 * @Description: 开启自动推送线程  
	 * @param count  void    返回类型 
	 */
	public synchronized void start(int count) {
		LOGGER.info("短信服务自动推送启动");
		
		// 1. 保证只执行一次
		if (pool != null) {
			stop();
		}

		// 2. 把队列赋予消费者
		consumer.setQueue(queue);
		
		// 3. 启动线程
		if (count != 0) {
			maxCount = count;
		}
		pool = Executors.newFixedThreadPool(maxCount);
		for (int i = 0; i < maxCount; i++) {
			pool.execute(consumer);
		}
	}	
	
	/**
	 * @Title: stop  
	 * @Description: 清除队列、关闭线程池 
	 */
	@PreDestroy
	private void stop() {
		try {
			Thread.sleep(3000);
			queue.clear();
		} catch (InterruptedException e) {
			LOGGER.info("队列清除异常",e);
		}
		if(pool != null){
			LOGGER.info("进行关闭线程池...");
			pool.shutdownNow();
			pool = null;
		}
	}
}
