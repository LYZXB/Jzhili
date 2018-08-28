package com.jzl.service;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.User;

/**
 * 
 * @ClassName: UserService  
 * @Description: 用户处理接口  
 * @author Sombra  
 * @date 2017年12月18日 下午3:38:44  
 * @version V1.0
 */
public interface UserService {
	
	/**
	 * 	<ol>
	 * 		<li> 判断是否可以注册
	 * 		<li> 生成数据权限标志
	 * 		<li> 用户信息存入数据库存入缓存
	 * 	</ol>
	 * 
	 * @Title: register  
	 * @Description: 注册  
	 * @param user
	 * @return  Message    返回类型 
	 */
	Message register(User user);
	
	/**
	 * 
	 * @Title: login  
	 * @Description: 登录  
	 * @param user
	 * @return  Message    成功失败信息 
	 */
	Message login(User user);
	
	/**
	 * 
	 * @Title: isexist  
	 * @Description:   
	 * @param userName
	 * @return  boolean    返回类型 
	 */
	boolean isexist(String userName);
	
	/**
	 * @Title: findUserInfo  
	 * @Description: 查询用户信息  
	 * @param userName
	 * @return  User    返回类型 
	 */
	User findUserInfoByUserName(String userName);
	
	/**
	 * @Title: update  
	 * @Description: 修改用户信息
	 * @param user
	 * @return  Message    返回类型 
	 */
	Message update(User user);
	
}
