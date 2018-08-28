package com.jzl.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class HelloWorldAspect implements Ordered {

	@Override
	public int getOrder() {
		return 2;
	}
	
	@Pointcut()
	public void helloWorld() {
		
	}

}
