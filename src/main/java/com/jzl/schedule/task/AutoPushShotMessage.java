package com.jzl.schedule.task;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzl.beans.common.SMSTemplateParam;
import com.jzl.beans.user.PushSetting;
import com.jzl.common.ScheduledTask;
import com.jzl.enums.PushTypeEnum;
import com.jzl.enums.ScheduledTaskStatusEnum;
import com.jzl.schedule.queue.SMSQueueProducer;
import com.jzl.service.BirthdayService;
import com.jzl.service.PushSettingService;
import com.jzl.utils.JedisUtils;
import com.jzl.utils.RedisDataRulerUtils;

/**
 * <ol>
 * <li>获取需要推送的用户
 * <li>根据用户推送设置，获取需要推送的数据
 * <li>根据用户推送设置，把数据推入符合的推送方式队列
 * </ol>
 *
 * @ClassName: AutoPushShotMessage
 * @Description: 自动推送短信
 * @author Sombra
 * @date 2018年4月9日 下午12:27:19
 * @version V1.0
 */
@Component
public class AutoPushShotMessage extends ScheduledTask {

	private Logger LOGGER = Logger.getLogger(AutoPushShotMessage.class);

	@Autowired
	SMSQueueProducer smsProducer;
	@Autowired
	PushSettingService pushSettingService;
	@Autowired
	BirthdayService birthdayService;

	private int count = 1000;
	private String taskId = "autoUpdateBirthday";

	@Override
	protected void exec() {
		// 0. 判断是否需要执行任务
		if (isExec()) {
			return;
		}

		try {
			// 1. 获取需要推送的用户推送设置
			List<PushSetting> pushSettings = pushSettingService.findNeedPushData(count);
			
			// 2. 根据推送方式分组
			Map<Integer, List<PushSetting>> pushSettingMap = pushSettings.stream().collect(Collectors.groupingBy(push -> push.getPushType()));
			
			// 3. 推送短信数据
			List<PushSetting> SMSPushSettting = pushSettingMap.get(PushTypeEnum.SMS.getCode());
			for (PushSetting pushSetting : SMSPushSettting) {
				// a. 获取所有需要推送的生日数据
				Queue<SMSTemplateParam> smsQueue = birthdayService.findAllSMSPushData(pushSetting);
				
				// b. 生日数据添加到生产者队列
				smsProducer.addQueue(smsQueue);
				
				// c. 推送设置推送时间更新
				pushSettingService.refreshPushDate(pushSetting.getGco());
			}
			LOGGER.info("短信推送数据添加到生产者队列");

		} catch (Exception e) {
			LOGGER.info("执行自动任务出错", e);
		} finally {
			// 更新执行状态为未执行
			JedisUtils.set(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId),
					ScheduledTaskStatusEnum.NOT_EXEC.getCode());
		}

	}

	/**
	 * 
	 * @Title: isExec
	 * @Description: 判断自动任务是否在执行状态
	 * @return boolean 返回类型
	 */
	private boolean isExec() {
		String status = JedisUtils.get(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId));
		if (ScheduledTaskStatusEnum.isExec(status)) {
			return true;
		}
		// 更新执行状态为执行中
		JedisUtils.set(RedisDataRulerUtils.getScheduledTaskStatusKey(taskId),
				ScheduledTaskStatusEnum.IN_EXEC.getCode());
		return false;
	}
}
