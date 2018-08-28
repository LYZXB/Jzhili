package com.jzl.schedule.queue;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzl.beans.birthday.Birthday;
import com.jzl.service.BirthdayService;

/**
 * 
 * @ClassName: UpdateBirthdayQueueConsumer  
 * @Description: 更新生日数据消费者  
 * @author Sombra  
 * @date 2018年3月28日 下午3:29:08  
 * @version V1.0
 */
@Component
public class UpdateBirthdayQueueConsumer implements Runnable {
	private Logger LOGGER = Logger.getLogger(UpdateBirthdayQueueConsumer.class);
	
	@Autowired
	private BirthdayService birthdayService;
	/** 待处理队列 */
	private BlockingQueue<List<Birthday>> queue = null;
	
	public void setQueue(BlockingQueue<List<Birthday>> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		boolean run = true;
		while(run) {
			run = handle();
		}
	}
	
	/**
	 * 
	 * @Title: handle  
	 * @Description: 更新生日数据处理  
	 * @return  boolean    返回类型 
	 */
	private boolean handle() {
		try {
			List<Birthday> birthdayList = queue.take();
			birthdayService.updateBirthday(birthdayList);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.info("执行更新生日数据线程错误: ", e);
		}
		return true;
	}
	
	
}
