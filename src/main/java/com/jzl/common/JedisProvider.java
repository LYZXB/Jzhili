package com.jzl.common;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jzl.utils.PropertiesUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @ClassName: JedisProvider  
 * @Description: jedis提供者  
 * @author Sombra  
 * @date 2017年12月19日 下午5:47:13  
 * @version V1.0
 */
public class JedisProvider {
	private static Logger LOGGER = Logger.getLogger(JedisProvider.class);
	
	private static JedisPool jedisPool = null;
	
	/**
	 * 
	 * @Title: getJedisPool  
	 * @Description: 获取jedispool实例
	 * @return  JedisPool    返回类型 
	 */
	private static JedisPool getJedisPoolInstance() {
		LOGGER.info("创建Redis对象");
		if (jedisPool == null) {
			synchronized (JedisPool.class) {
				if (jedisPool == null) {
					Properties properties = PropertiesUtils.loadProperties("redis.properties");
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(1024); // 可用连接实例的最大数目,如果赋值为-1,表示不限制.
					config.setMaxIdle(5); // 控制一个Pool最多有多少个状态为idle(空闲的)jedis实例,默认值也是8
					config.setMaxWaitMillis(1000 * 100); // 等待可用连接的最大时间,单位毫秒,默认值为-1,表示永不超时/如果超过等待时间,则直接抛出异常
					config.setTestOnBorrow(true); // 在borrow一个jedis实例时,是否提前进行validate操作,如果为true,则得到的jedis实例均是可用的
					String password = properties.getProperty("redis.password");
					String host = properties.getProperty("redis.host");
					Integer port = Integer.valueOf(properties.getProperty("redis.port"));
					if (StringUtils.isBlank(password)) {
						jedisPool = new JedisPool(config, host, port);
					} else {
						jedisPool = new JedisPool(config, host, port, 60000, password);
					}
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
		return getJedisPoolInstance().getResource();
	}
	
	
	/** 
     * 释放jedis资源 
     * @param jedis 
     */  
     public static void returnResource(final Jedis jedis) {  
    	 LOGGER.info("销毁Redis对象");
    	 
         if (jedis != null) {  
              jedis.close();
         }  
     }  
}
