package com.xxx.oam.service;

import com.jfinal.aop.Before;
import com.xxx.oam.interceptor.TestInterceptor;

public class TestMethodInterceptor {
	
	@Before(TestInterceptor.class)
	public void outString() {
		System.out.println("这个是被针对的方法");
	}
}
