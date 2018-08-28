package com.jzl.service;

import java.util.List;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.PushSetting;
import com.jzl.enums.PushTypeEnum;

/**
 * 
 * @ClassName: PushSettingService  
 * @Description: 推送设置接口  
 * @author Sombra  
 * @date 2018年4月9日 下午4:35:29  
 * @version V1.0
 */
public interface PushSettingService {

	/**
	 * <ol>
	 * 	<li> 推送类型不为不推送
	 * 	<li> 上次 推送时间小于当天0点
	 * </ol>
	 * @Title: findAllNeedPushData  
	 * @Description: 查询出需要推送的推送设置。 
	 * @return  List<PushSetting>    返回类型 
	 */
	List<PushSetting> findNeedPushData(Integer count);
	
	/**
	 * @Title: findPushByUsername  
	 * @Description: 通过用户名和推送类型查找推送设置
	 * @param userName
	 * @param pushType
	 * @return  PushSetting    返回类型 
	 */
	PushSetting findPushByUsername(String userName, PushTypeEnum pushType);
	
	/**
	 * @Title: savePushSet  
	 * @Description: 如果是不存在，新增推送设置。存在的话，更新。  
	 * @param pushSet
	 * @return  Message    返回类型 
	 */
	Message savePushSet(PushSetting pushSet);
	
	/**
	 * @Title: refreshPushDate  
	 * @Description: 刷新推送时间  
	 * @return  Message    返回类型 
	 */
	Message refreshPushDate(String gco);
}
