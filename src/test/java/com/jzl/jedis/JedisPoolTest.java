package com.jzl.jedis;

import java.util.Properties;

import com.jzl.utils.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	private static JedisPool jedisPool = null;
	private static Jedis jedis = null;
	
	/**
	 * 
	 * @Title: getJedisPool  
	 * @Description: 获取jedispool实例
	 * @return  JedisPool    返回类型 
	 */
	private static JedisPool getJedisPoolInstance() {
		if (jedisPool == null) {
			synchronized (JedisPool.class) {
				if (jedisPool == null) {
					Properties properties = PropertiesUtils.loadProperties("redis.properties");
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(1024); // 可用连接实例的最大数目,如果赋值为-1,表示不限制.
					config.setMaxIdle(5); // 控制一个Pool最多有多少个状态为idle(空闲的)jedis实例,默认值也是8
					config.setMaxWaitMillis(1000 * 100); // 等待可用连接的最大时间,单位毫秒,默认值为-1,表示永不超时/如果超过等待时间,则直接抛出异常
					config.setTestOnBorrow(true); // 在borrow一个jedis实例时,是否提前进行validate操作,如果为true,则得到的jedis实例均是可用的
					jedisPool = new JedisPool(config, properties.getProperty("redis.host"), Integer.valueOf(properties.getProperty("redis.port")));
				}
			}
		}
		
		return jedisPool;
	}
	
	/**
	 * 
	 * @Title: getJedisInstance  
	 * @Description: 获取jedis实例  
	 * @return  Jedis    返回类型 
	 */
	public static synchronized Jedis getJedisInstance() {
		if (jedis == null) {
			synchronized (Jedis.class) {
				if (jedis == null) {
					jedis = getJedisPoolInstance().getResource();
				}
			} 
		}
		return jedis;
	}
	
	
	/** 
     * 释放jedis资源 
     * @param jedis 
     */  
     public static void returnResource(final Jedis jedis) {  
         if (jedis != null) {  
              jedis.close();
         }  
     }  
}
