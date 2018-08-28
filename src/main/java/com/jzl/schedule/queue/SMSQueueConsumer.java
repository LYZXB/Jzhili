package com.jzl.schedule.queue;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.jzl.beans.common.SMSTemplateParam;
import com.jzl.utils.sms.SMSUtil;

/**
 * 
 * @ClassName: SMSQueueConsumer  
 * @Description: 短信服务队列消费者  
 * @author Sombra  
 * @date 2018年3月30日 下午6:03:29  
 * @version V1.0
 */
@Component
public class SMSQueueConsumer implements Runnable{
	private Logger LOGGER = Logger.getLogger(SMSQueueConsumer.class);

	private BlockingQueue<SMSTemplateParam> queue = null;
	
	public void setQueue(BlockingQueue<SMSTemplateParam> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// 调用短信服务
		boolean isRun = true;
		while(isRun) {
			isRun = handle();
		}
	}
	
	/**
	 * 
	 * @Title: handle  
	 * @Description: 处理接口  
	 * @return  boolean    返回类型 
	 */
	private boolean handle() {
		try {
			SMSTemplateParam param = queue.take();
			SMSUtil.sendSms(param);
		} catch (ClientException e) {
			e.printStackTrace();
			LOGGER.info("推送短信失败", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.info("推送短信失败", e);
		}
		return true;
	}
	
}
