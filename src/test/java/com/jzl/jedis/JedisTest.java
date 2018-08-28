package com.jzl.jedis;

import org.junit.Test;

import com.jzl.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	@Test
	public void poolTest() {
		Jedis jedis = JedisPoolTest.getJedisInstance();
		System.out.println(jedis.get("test"));
	}
	
	@Test
	public void jedieTest() {
		System.out.println(JedisUtils.get("test"));
	}
}
