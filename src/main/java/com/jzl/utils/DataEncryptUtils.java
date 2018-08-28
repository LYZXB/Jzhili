package com.jzl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: MD5Utils  
 * @Description: 加解密工具类  
 * @author Sombra  
 * @date 2017年12月26日 下午6:20:36  
 * @version V1.0
 */
public class DataEncryptUtils {
	
	/**
	 * 传入字符串，返回不可逆加密数据
	 * 
	 * @Title: encryption
	 * @Description: md5+base64加密
	 * @param encryStr
	 * @return String 返回类型
	 * @throws NoSuchAlgorithmException
	 */
	public static String oneWayEncryption(String encryStr) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64 = new BASE64Encoder();
		String newStr = base64.encode(md5.digest(encryStr.getBytes()));
		return newStr;
	}
	
	private static String twoWayEncryption(String encryStr) {
		return "";
	}
	
	private static String twoWayDecryption(String decryStr) {
		return "";
	}
}
