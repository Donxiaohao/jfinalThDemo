package com.xxx.oam.validator;

import java.lang.reflect.Method;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import com.xxx.oam.entity.User;

public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		System.out.println("进入登录验证");
		User user = User.dao.findById(1);
		if (StrKit.isBlank(controller.getPara("name"))) {
			addError("nameMsg", "请输入用户名");
		}else if(StrKit.isBlank(controller.getPara("password"))) {
			addError("passwordMsg", "请输入用密码");
		}else if(!user.getStr("name").equals(controller.getPara("name"))) {
			addError("nameMsg", "用户名不存在");
		}else if(!user.getStr("password").equals(controller.getPara("password"))){
			addError("passwordMsg", "密码错误");
		}
	}
	@Override
	protected void handleError(Controller c) {
		c.keepPara("name");
		c.keepPara("password");
		c.render("/html/login.html");
		System.out.println("------------------------");
	}

}
