package com.xxx.oam.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class TestInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		System.out.println("进入针对单一方法的拦截器");
		inv.invoke();
		System.out.println("退出针对单一方法的拦截器");
	}

}
