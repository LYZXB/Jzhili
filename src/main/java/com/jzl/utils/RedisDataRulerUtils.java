package com.jzl.utils;

/**
 * 
 * @ClassName: RedisDataRulerUtils  
 * @Description: Redis数据存储规则工具类  
 * @author Sombra  
 * @date 2017年12月19日 下午6:09:48  
 * @version V1.0
 */
public class RedisDataRulerUtils {
	
	
	/**
	 * 用户数据权限标志前缀
	 */
	private static final String USER_DATASIGN_PRE = "USER_DATASIGNS__";
	/**
	 * 用户默认数据权限标志前缀
	 */
	private static final String USER_DEFAULT_DATASIGN_PRE = "USER_DEFAULT_DATASIGNS__";
	/**
	 * 用户信息前缀
	 */
	private static final String USERINFO_PRE = "USERINFO__";
	/**
	 * 用户Hash前缀
	 */
	private static final String USER_HASH_PRE = "USER_HASH";
	
	/**
	 * 用户令牌（每次进入系统变更）
	 */
	private static final String USER_AWAYS_CHANGE_TOKEN_PRE = "USER_AWAYS_CHANGE_TOKEN__";
	
	/**
	 * 用户令牌（每次登陆变更）
	 */
	private static final String USER_LOGIN_CHANGE_TOKEN_PRE = "USER_LOGIN_CHANGE_TOKEN__";
	
	/**
	 * 自动作业执行状态前缀
	 */
	private static final String SCHEDULED_TASK_STATU_PRE = "SCHEDULED_TASK_STATU__";

	/**
	 * @Title: getUserDataSignKey  
	 * @Description: 获取用户权限标志Key
	 * @param userName
	 * @return  String    USER_DATASIGNS__userName 
	 */
	public static String getAllUserDataSignKey(String userName) {
		return USER_DATASIGN_PRE + userName;
	}
	/**
	 * 
	 * @Title: getDefaultUserDataSignKey  
	 * @Description: 获取用户默认权限标志key
	 * @param userName
	 * @return  String    返回类型 
	 */
	public static String getDefaultUserDataSignKey(String userName) {
		return USER_DEFAULT_DATASIGN_PRE + userName;
	}
	/**
	 * @Title: getUserInfoKey  
	 * @Description: 获取用户信息Key  
	 * @param userName
	 * @return  String    USERINFO__userName
	 */
	public static String getUserInfoKey(String userName) {
		return USERINFO_PRE + userName;
	}
	/**
	 * 
	 * @Title: getUserHashKey  
	 * @Description: 获取用户Hash的Key  
	 * @return  String    USER_HASH
	 */
	public static String getUserHashKey() {
		return USER_HASH_PRE;
	}
	/**
	 * 
	 * @Title: getUserAwaysChangeTokenKey  
	 * @Description: 获得用户登陆令牌（每次进入系统变更）key
	 * @param userName
	 * @return  String    返回类型 
	 */
	public static String getUserAwaysChangeTokenKey (String userName) {
		return USER_AWAYS_CHANGE_TOKEN_PRE + userName;
	}
	/**
	 * 
	 * @Title: getUserLoginChangeTokenKey  
	 * @Description: 获得用户登陆令牌（每次登陆系统变更）key
	 * @param userName
	 * @return  String    返回类型 
	 */
	public static String getUserLoginChangeTokenKey(String userName) {
		return USER_LOGIN_CHANGE_TOKEN_PRE + userName;
	}
	
	/**
	 * 
	 * @Title: getScheduledTaskStatuKey  
	 * @Description: 获取定时任务key  
	 * @param taskId
	 * @return  String    返回类型 
	 */
	public static String getScheduledTaskStatusKey(String taskId) {
		return SCHEDULED_TASK_STATU_PRE + taskId;
	}
	
}
