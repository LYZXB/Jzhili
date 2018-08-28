package com.jzl.common;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: ScheduledTask  
 * @Description: 定时器任务  
 * @author Sombra  
 * @date 2018年3月27日 下午6:24:55  
 * @version V1.0
 */
public abstract class ScheduledTask implements Runnable {
	Logger LOGGER = Logger.getLogger(ScheduledTask.class);
	
	private String taskid;// 任务id
	private String tigger;// cron时间表达式
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getTigger() {
		return tigger;
	}
	public void setTigger(String tigger) {
		this.tigger = tigger;
	}
	
	@Override
	public void run() {
		LOGGER.info("执行当前任务线程编号: " + Thread.currentThread().getId());
		exec();
	}
	
	protected abstract void exec();
}
