package com.jzl.repository;

import com.jzl.beans.common.Permission;

/**
 * 
 * @ClassName: DataPermissionRepository  
 * @Description: 数据权限数据操作接口  
 * @author Sombra  
 * @date 2017年12月19日 下午3:04:43  
 * @version V1.0
 */
public interface DataPermissionRepository {
	
	/**
	 * 
	 * @Title: queryDataPermissionByUser  
	 * @Description: 通过用户名，查询用户的数据权限  
	 * @param userName
	 * @return  String   用户权限标志字符串，格式： 标志1,标志2,标志3
	 */
	String queryAllDataPermissionByUserName(String userName);
	
	/**
	 * 
	 * @Title: queryDataPermissionByUser  
	 * @Description: 通过用户名和类型，查询用户的数据权限  
	 * @param userName
	 * @return  String   用户权限标志字符串
	 */
	String queryDataPermissionByUserNameAndType(String userName, Integer type);
	
	/**
	 * 
	 * @Title: add  
	 * @Description: 插入数据权限数据  
	 * @param permission
	 * @return  Integer    返回类型 
	 */
	Integer add(Permission permission);
}
