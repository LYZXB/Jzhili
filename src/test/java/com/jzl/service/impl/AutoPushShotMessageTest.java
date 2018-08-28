package com.jzl.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jzl.schedule.task.AutoPushShotMessage;

@RunWith(SpringJUnit4ClassRunner.class)  
//引入Spring配置  
@ContextConfiguration({"classpath*:/applicationContext.xml"})  
public class AutoPushShotMessageTest {
	
	@Autowired
	AutoPushShotMessage autoPush;
	
	@Test
	public void execTest() {
		// 反射出方法
			try {
				Method method = AutoPushShotMessage.class.getDeclaredMethod("exec");
				method.setAccessible(true);
				method.invoke(autoPush);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
	}
}
