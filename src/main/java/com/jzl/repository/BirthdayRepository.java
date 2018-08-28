package com.jzl.repository;

import java.util.List;

import com.jzl.beans.birthday.Birthday;
import com.jzl.beans.common.PageBean;

/**
 * 
 * @ClassName: BirthdayRepository  
 * @Description: 生日数据操作接口  
 * @author Sombra  
 * @date 2017年11月21日 上午11:58:12  
 * @version V1.0
 */
public interface BirthdayRepository {
	
	/**
	 * 
	 * @Title: queryAll  
	 * @Description: 查询所有的生日数据  
	 * @return  List<Birthday>    返回类型 
	 * @throws
	 */
	List<Birthday> queryAll(String dataSigns, PageBean pagebean);
	/** 查询生日数据总量 */
	Long queryAllCount(String dataSigns);
	
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
	 * @Description: 新增生日信息  
	 * @param birthday
	 * @return  Integer    返回类型 
	 */
	Integer insertBirthday(Birthday birthday);
	
	/**
	 * 
	 * @Title: updateBirthday  
	 * @Description: 修改生日信息
	 * @param birthday
	 * @return  Integer    返回类型 
	 */
	Integer updateBirthday(Birthday birthday);
	
	/**
	 * 
	 * @Title: updateBithday  
	 * @Description: 批量更新生日数据
	 * @param birthdays
	 * @return  Integer    返回类型 
	 */
	void updateBirthday(List<Birthday> birthdays);
	
	/**
	 * 查询修改时间小于当天零点的数据
	 * @Title: findAllNeedUpdateBirthday  
	 * @Description: 寻找需要更新的生日数据  
	 * @param count
	 * @return  List<Birthday>    返回类型 
	 */
	List<Birthday> findAllNeedUpdateBirthday();
	
	/**
	 * 
	 * @Title: findAllNeedPushByLunarDays  
	 * @Description: 根据农历生日还剩时间，查询所有需要推送的生日数据
	 * @return  List<Birthday>    返回类型 
	 */
	List<Birthday> findAllNeedPushByLunarDays(Integer days);
}
