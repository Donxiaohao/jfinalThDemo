package com.xxx.oam.service;

import com.jfinal.aop.Before;
import com.xxx.oam.interceptor.TestInterceptor;

public class TestMethodInterceptor {
	
	/*@Before(TestInterceptor.class)
	public Integer outString(int a) {
		Integer result=a+1;
		if(result==2) {
			try {
				Thread.sleep(1000*15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true) {
				
			}
		}
		System.out.println("这个是被针对的方法");
		return result;
	}*/
	@Before(TestInterceptor.class)
	public void outString() {
		System.out.println("进入方法~~~~~~");
		try {
			throw new RuntimeException("test");
		} catch (Exception e) {
			
		}
	}
}
