package com.xxx.oam.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.xxx.oam.controller.IndexController;
import com.xxx.oam.controller.LoginController;
import com.xxx.oam.entity.User;

public class BaseConfig extends JFinalConfig{
	/**
	 * 配置基本JFinal常量
	 */
	@Override
	public void configConstant(Constants me) {
		PropKit.use("mysql_config.txt");
		// 配置是否在控制台 打印输出 可在配置文件内进行动态更改
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JFINAL_TEMPLATE);
		me.setUrlParaSeparator("&");
	}
	
	/**
	 * 配置页面路由
	 */
	@Override
	public void configRoute(Routes me) {
		// 设置视图渲染路径
		me.setBaseViewPath("/html");
		me.add("/", LoginController.class);
		me.add("/index", IndexController.class);
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		Prop p = PropKit.use("mysql_config.txt");
		DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"),
                p.get("user"),
                p.get("password"));
		//设置sqlite驱动程序
		dp.setDriverClass("org.sqlite.JDBC");
	    me.add(dp);
	    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
	    me.add(arp);
	    arp.addMapping("user", User.class);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
