package com.xxx.oam.controller;

import com.jfinal.aop.Before;
/*import com.jfinal.core.Controller;

public class IndexController extends Controller{
	//加载时 配置好config路由 可默认加载首页面
	public void index() {
		render("index.html");
	}
	
}*/
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.xxx.oam.entity.User;
import com.xxx.oam.interceptor.OamInterceptor;
import com.xxx.oam.validator.LoginValidator;


/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
@Before(OamInterceptor.class)
public class IndexController extends Controller {
	public void index() {
		//测试页面生成二维码
//		renderQrCode("TH", 100, 100);
		render("index.html");
	}
	
	@Before(LoginValidator.class)
	public void login() {
		setSessionAttr("loginUser", User.dao.findById(1));
		renderText("login success");
	}
}