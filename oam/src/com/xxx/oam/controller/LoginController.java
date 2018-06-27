package com.xxx.oam.controller;

import java.util.List;

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
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xxx.oam.entity.TestUser;
import com.xxx.oam.interceptor.OamInterceptor;
import com.xxx.oam.validator.LoginValidator;


/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
//@Before(OamInterceptor.class)
public class LoginController extends Controller {
	public void index() {
		System.out.println("进入LoginController index方法");
		//测试页面生成二维码
//		renderQrCode("TH", 100, 100);
		/*测试多表查询映射问题
		List<Record> find = Db.find("select * from user u ,user_message um where u.id=um.user_id");
		for (Record record2 : find) {
			System.out.println(record2.toString());
		}*/
		render("/html/login.html");
	}
	
	@Before(LoginValidator.class)
	public void login() {
		setSessionAttr("loginUser", TestUser.dao.findById(1));
		System.out.println("进入login");
		redirect("/index");
	}
	
	public void exit() {
		System.out.println("只有登录的用户才能打印哦~~~");
		redirect("www.baidu.com");
	}
}