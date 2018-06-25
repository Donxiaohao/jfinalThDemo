package com.xxx.oam.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.xxx.oam.entity.TestUser;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		System.out.println("全局拦截器开始"+inv.getActionKey()+"-----"
							+inv.getControllerKey()+"-----"+inv.getMethodName());
		if(!inv.getControllerKey().equals("/login")) {
			TestUser user = inv.getController().getSessionAttr("loginUser");
			if(user==null) {
				System.out.println("用户非法访问 返回登录页面");
				inv.getController().redirect("/login");
			}else {
				inv.invoke();
			}
		}else {
			inv.invoke();
		}
		System.out.println("全局拦截器结束");
	}

}
