package com.jzl.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.PushSetting;
import com.jzl.enums.PushTypeEnum;
import com.jzl.repository.PushSettingRepository;
import com.jzl.service.IncrementerService;
import com.jzl.service.PushSettingService;

/**
 * 
 * @ClassName: PushSettingServiceImpl  
 * @Description: 推送设置接口实现  
 * @author Sombra  
 * @date 2018年4月9日 下午4:35:42  
 * @version V1.0
 */
@Service
public class PushSettingServiceImpl implements PushSettingService {

	@Autowired
	PushSettingRepository pushSettingRepository;
	@Autowired
	IncrementerService incrementerService;
	
	@Override
	public List<PushSetting> findNeedPushData(Integer count) {
		return pushSettingRepository.findNeedPushData(count);
	}

	@Override
	public Message savePushSet(PushSetting pushSet) {
		// 是否存在
		boolean isExist = StringUtils.isNotEmpty(pushSet.getGco());
		
		// 存在
		if (isExist) {
			return pushSettingRepository.updatePushSet(pushSet) == 1 ? Message.SUCCESS : Message.FAILURE;
		}
		pushSet.setGco(incrementerService.getNextNumber("pushsetting"));
		return pushSettingRepository.insertPushSet(pushSet) == 1 ? new Message(true, pushSet.getGco()) : Message.FAILURE;
	}

	@Override
	public Message refreshPushDate(String gco) {
		return pushSettingRepository.refreshPushDate(gco);
	}

	@Override
	public PushSetting findPushByUsername(String userName, PushTypeEnum pushType) {
		return pushSettingRepository.findPushByUsername(userName);
	}

}
