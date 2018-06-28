package com.xxx.oam.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.xxx.oam.controller.IndexController;
import com.xxx.oam.controller.LoginController;
import com.xxx.oam.entity.TestUser;
import com.xxx.oam.interceptor.LoginInterceptor;
import com.xxx.oam.plugin.I18NPlugin;
import com.xxx.oam.task.RemoveTask;
import com.xxx.oam.util.LogTimerTask;

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
//		me.setViewType(ViewType.FREE_MARKER);
		me.setUrlParaSeparator("&");
		//设置国际化
		me.setI18nDefaultBaseName("i18n");
	}
	
	/**
	 * 配置页面路由
	 */
	@Override
	public void configRoute(Routes me) {
		// 设置视图渲染路径
//		me.setBaseViewPath("/html");    这个很尴尬
		me.add("/", LoginController.class,"/login");
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
//		dp.setDriverClass("org.sqlite.JDBC");
	    me.add(dp);
	    ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
	    me.add(arp);
	    arp.addMapping("user", TestUser.class);
		
	    //国际化键值对加载
//	    me.add(new I18NPlugin());
	    
	    //配置定时器
//	    Cron4jPlugin cp = new Cron4jPlugin();
//	    cp.addTask("*/1 * * * *", new RemoveTask());
//	    me.add(cp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//登录拦截器验证
		me.add(new LoginInterceptor());
		//国际化拦截器
		me.add(new I18nInterceptor());
		//添加控制层全局拦截器
//		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterJFinalStart(){
		/*System.out.println("任务启动了  我也运行了~~~");
		Timer timer = new Timer();
		LogTimerTask logTimerTask = new LogTimerTask(timer);
		timer.schedule(logTimerTask, 2000);*/
	}
}
