package com.jzl.service;

import java.util.List;
import java.util.Queue;

import com.jzl.beans.birthday.Birthday;
import com.jzl.beans.common.LayTableData;
import com.jzl.beans.common.PageBean;
import com.jzl.beans.common.SMSTemplateParam;
import com.jzl.beans.user.PushSetting;

/**
 * 
 * @ClassName: BirthdayService  
 * @Description: 生日逻辑接口  
 * @author Sombra  
 * @date 2017年11月22日 下午5:11:24  
 * @version V1.0
 */
public interface BirthdayService {
	
	/**
	 * 
	 * @Title: findAll  
	 * @Description: 查询所有生日数据  
	 * @return  List<Birthday>    返回类型 
	 */
	LayTableData findAll(String userName, PageBean pagebean);
	
	/**
	 * 
	 * @Title: delBirthday  
	 * @Description: 根据编码删除生日数据  
	 * @param gco
	 * @return  Integer    返回类型 
	 */
	Integer delBirthday(String gco);
	
	/**
	 * 
	 * @Title: insertBirthday  
	 * @Description: 插入生日数据  
	 * @param birthday 还需要填充字段：下一次生日、年龄、权限标志、农历生日
	 * @return  Birthday 用来展示，但是能不能在前台直接组装呢？TODO 
	 */
	Birthday insertBirthday(Birthday birthday, String userName);
	
	/**
	 * 
	 * @Title: updateBirthday  
	 * @Description: 修改生日数据  
	 * @param birthday 需要字段： 下一次生日、年龄、农历生日
	 * @return  Integer    返回类型 
	 */
	Integer updateBirthday(List<Birthday> birthdays);
	
	/**
	 * 查询修改时间小于当天零点的数据
	 * @Title: findAllNeedUpdateBirthday  
	 * @Description: 寻找需要更新的生日数据  
	 * @param count
	 * @return  List<Birthday>    返回类型 
	 */
	List<Birthday> findAllNeedUpdateBirthday(int count);
	
	/**
	 * <ol>
	 * 	<li> 需要推送
	 * 	<li> 距离下次推送天数匹配
	 * </ol>
	 * @Title: findAllSMSPushData  
	 * @Description: 查询所有需要短信推送的数据  
	 * @return  Queue<SMSTemplateParam>    返回类型 
	 */
	Queue<SMSTemplateParam> findAllSMSPushData(PushSetting setting);
}
