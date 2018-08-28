package com.jzl.utils;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jzl.beans.user.User;

/**
 * 
 * @ClassName: CookieUtils  
 * @Description: cookie管理工具类  
 * @author Sombra  
 * @date 2018年3月21日 下午4:40:17  
 * @version V1.0
 */
public class CookieUtils {
	
	/** 检查通过 */
	public static final Integer CHECK__SUSESSE = 0;
	/** 正常错误 */
	public static final Integer CHECK__DEFAULT_FAILED = 1;
	/** cookie盗取错误 */
	public static final Integer CHECK__THEFT_FAILED = 2;
	

	/**
	 * <ol>
	 * 	<li> 都正确，则进入系统
	 * 	<li> token__aways_change不同，token__login_change相同，返回登录页面，并提示用户账号可能被盗
	 * 		(cookie被盗用的情况，服务器和当前浏览器的token__aways_change不同)
	 * </ol> 
	 * @Title: check  
	 * @Description:  检查cookie是否正确
	 * @param cookies
	 * @return  Integer    返回类型 
	 */
	public static Integer check(Map<String, String> cookieMap) {
		// 0. 检查cookies
		String awaysChangeToken = cookieMap.get(User.TOKEN__AWAYS_CHANGE);
		String loginChangeToken = cookieMap.get(User.TOKEN__LOGIN_CHANGE);
		if (StringUtils.isBlank(awaysChangeToken) || StringUtils.isBlank(loginChangeToken)) {
			return CHECK__DEFAULT_FAILED;
		}
		String userName = loginChangeToken.split("_")[0];
		if (StringUtils.isBlank(userName)) {
			return CHECK__DEFAULT_FAILED;
		}
		
		// 1. 获取服务器的cookies
		String redisAwaysChangeToken = JedisUtils.get(RedisDataRulerUtils.getUserAwaysChangeTokenKey(userName));
		String redisLoginChangeToken = JedisUtils.get(RedisDataRulerUtils.getUserLoginChangeTokenKey(userName));
		if (StringUtils.isBlank(redisAwaysChangeToken) || StringUtils.isBlank(redisLoginChangeToken)) {
			return CHECK__DEFAULT_FAILED;
		}
		if (!awaysChangeToken.equals(redisAwaysChangeToken) || !loginChangeToken.equals(redisLoginChangeToken)) {
			if (loginChangeToken.equals(redisLoginChangeToken)) {
				return CHECK__THEFT_FAILED;
			}
			return CHECK__DEFAULT_FAILED;
		}
		
		// 2. 比较，返回结果
		return CHECK__SUSESSE;
	}
	
	/**
	 * <ol>
	 * 	<li> 刷新浏览器的cookie值
	 * 	<li> 刷新服务器的cookie值
	 * </ol>
	 * @Title: refreshAwaysChangeToken  
	 * @Description: 刷新token__aways_change值
	 * @return 新的token值
	 */
	public static String refreshAwaysChangeToken(String userName) {
		// 生成
		String awaysChangeToken = getNewAwaysChangeToken();
		
		// 存入服务器端
		JedisUtils.set(RedisDataRulerUtils.getUserAwaysChangeTokenKey(userName), awaysChangeToken);
		
		return awaysChangeToken;
	}
	
	/**
	 * 生成算法：md5(时间+IP地址+随机数)
	 * @Title: getNewAwaysChangeToken  
	 * @Description: 生成登陆令牌
	 * @return  String    返回类型 
	 */
	private static String getNewAwaysChangeToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ip = request.getRemoteAddr();
		try {
			return DataEncryptUtils.oneWayEncryption(DateUtil.long2String(System.currentTimeMillis()) + ip + RandomStringUtils.random(100000) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: refreshLoginChangeToken  
	 * @Description: 刷新登陆令牌（每次登陆改变）
	 * @param userName
	 * @return  String    返回类型 
	 */
	public static String refreshLoginChangeToken(String userName) {
		// 生成
		String loginChangeToken = getNewLoginChangeToken(userName);
		
		// 存入服务器端
		JedisUtils.set(RedisDataRulerUtils.getUserLoginChangeTokenKey(userName), loginChangeToken);
		
		return loginChangeToken;
	}
	
	/**
	 * 生成算法：用户名_md5(用户名+当前时间)
	 * @Title: getNewLoginChangeToken
	 * @Description: 生成用户登陆令牌
	 * @return  String    返回类型 
	 */
	private static String getNewLoginChangeToken(String userName) {
		try {
			return userName + "_" + DataEncryptUtils.oneWayEncryption(userName+DateUtil.long2String(System.currentTimeMillis()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
