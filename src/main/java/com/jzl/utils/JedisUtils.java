package com.jzl.utils;

import org.apache.log4j.Logger;

import com.jzl.common.JedisProvider;

import redis.clients.jedis.Jedis;

/**
 * 
 * @ClassName: JedisUtils  
 * @Description: redis操作工具类  
 * @author Sombra  
 * @date 2017年12月19日 下午5:50:11  
 * @version V1.0
 */
public class JedisUtils {
	private static Logger LOGGER = Logger.getLogger(JedisUtils.class);
	private static Jedis jedis = null;
	
	public static void set(String key, String value) {
		LOGGER.info("redis插入数据 key:"+ key + "; value:" + value);
		try {
			initJedis();
			jedis.set(key, value);
		} finally {
			JedisProvider.returnResource(jedis);
		}
	}
	
	public static void setex(String key, int seconds, String value) {
		try {
			initJedis();
			jedis.setex(key, seconds, value);
		} finally {
			JedisProvider.returnResource(jedis);
		}
	}
	
	
	public static String get(String key) {
		LOGGER.info("redis获取数据 key:"+ key);
		try {
			initJedis();
			return jedis.get(key);
		} finally {
			JedisProvider.returnResource(jedis);
		}
	}
	
	
	/**
	 * @Title: initJedis  
	 * @Description: 初始化Jedis，每个方法调用一次，防止出错 
	 */
	private static void initJedis() {
		jedis = JedisProvider.getJedisInstance();
	}
}
