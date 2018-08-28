package com.jzl.service;

/**
 * 
 * @ClassName: DataPermissionService  
 * @Description: 数据权限接口  
 * @author Sombra  
 * @date 2017年12月8日 下午4:10:52  
 * @version V1.0
 */
public interface DataPermissionService {

	/**
	 * 初始化某个用户权限  
	 * @Title: initDataPermissionForUser  
	 * @Description: 初始化某个用户权限  
	 * @param userName
	 * @return  String    返回类型 
	 */
	String initDataPermissionForUser(String userName);
	
	/**
	 * 
	 * @Title: queryDataPermissionByUser  
	 * @Description: 通过用户名，查询用户的所有数据权限  
	 * @param userName
	 * @return  String    返回类型 
	 */
	String queryAllDataPermissionByUserName(String userName);
	
	/**
	 * 
	 * @Title: queryDataPermiassionByUserNameAndType  
	 * @Description: 通过用户名，查询用户的默认数据权限  
	 * @param userName
	 * @return  String    返回类型 
	 */
	String queryDefaultDataPermiassionByUserName(String userName);
}
