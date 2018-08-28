package com.jzl.repository;

import com.jzl.beans.user.User;

/**
 * 
 * @ClassName: UserRepository  
 * @Description: 用户数据处理接口  
 * @author Sombra  
 * @date 2017年12月18日 下午3:40:45  
 * @version V1.0
 */
public interface UserRepository {
	
	/**
	 * 
	 * @Title: queryCountByUserName  
	 * @Description: 通过编码获取用户数据总数  
	 * @param userName
	 * @return  Long    返回类型 
	 */
	Long queryCountByUserName(String userName);
	
	/**
	 * 
	 * @Title: queryPasswordByUserName  
	 * @Description: 通过用户名查询用户密码
	 * @param userName
	 * @return  String    返回类型 
	 * @throws
	 */
	String queryPasswordByUserName(String userName);
	
	/**
	 * 
	 * @Title: save  
	 * @Description: 保存用户  
	 * @param user
	 * @return  Integer    返回类型 
	 */
	Integer save(User user);
	
	/**
	 * 
	 * @Title: findUserInfoByUserName  
	 * @Description: 根据用户名查找用户信息
	 * @param userName
	 * @return  User    返回类型 
	 */
	User findUserInfoByUserName(String userName);
	
	/**
	 * @Title: update  
	 * @Description: 修改用户  
	 * @param user
	 * @return  Integer    返回类型 
	 */
	Integer update(User user);
}
