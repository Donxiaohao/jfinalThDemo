package com.xxx.oam.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.xxx.oam.entity.User;
import com.xxx.oam.util.DateUtil;

public class OamInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		inv.invoke();
		if(inv.getActionKey()!=null&&inv.getActionKey().equals("/login")) {
			User user = (User)inv.getController().getSessionAttr("loginUser");
//			if(user!=null) {
				System.out.println(user.getStr("name")+"在"+DateUtil.getNowDate()+"登录成功");
//			}
		}
	}
	
}
