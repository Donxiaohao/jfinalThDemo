package com.xxx.oam.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Test;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.druid.DruidPlugin;
import com.xxx.oam.service.TestMethodInterceptor;
public class TestTX {
	@Test
	public void testTX() {
		Prop p = PropKit.use("mysql_config.txt");
		DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"),
                p.get("user"),
                p.get("password"));
		ActiveRecordPlugin arp=new ActiveRecordPlugin("master",dp);
		dp.start();
		arp.start();
		
		DruidPlugin dp2 = new DruidPlugin("jdbc:mysql://localhost/test2?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
                "root",
                "root");
		ActiveRecordPlugin arp2=new ActiveRecordPlugin("slave",dp2);
		dp2.start();
		arp2.start();
		for(int i=0;i<3;i++) {
			Db.use("master").tx(new IAtom() {
				@Override
				public boolean run() throws SQLException {
					return Db.use("slave").tx(new IAtom() {
						@Override
						public boolean run() throws SQLException {
							for(int i=1;i<30;i++) {
								/*if(i==25) {
									throw new RuntimeException("测试");
								}*/
								Record set = new Record().set("name","testTx"+i);
								Db.use("slave").save("test_out", set);
							}
							Db.use("master").save("test_in",new Record().set("name", "success"));
							return true;
						}
					});
				}
			});
			try {
				System.out.println("sleep five s");
				Thread.sleep(5000);
				System.out.println("sleep over");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
	
	@Test
	public void testInterceptor() {
		TestMethodInterceptor aa = Duang.duang(new TestMethodInterceptor());
		aa.outString();
	}
	
	@Test
	public void testTX2() {
		DruidPlugin dp = new DruidPlugin("jdbc:mysql://192.168.11.67/nms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
                "nms",
                "nms");
		ActiveRecordPlugin arp=new ActiveRecordPlugin("master",dp);
		dp.start();
		arp.start();
		
		DruidPlugin dp2 = new DruidPlugin("jdbc:mysql://192.168.11.76/nms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
                "nms",
                "nms");
		ActiveRecordPlugin arp2=new ActiveRecordPlugin("slave",dp2);
		dp2.start();
		arp2.start();
		boolean flag=true;
		while(flag) {
			Record findFirst = Db.use("slave").findFirst("select * from table_sync_info where table_name='di_rule' and event = 1");
			System.out.println(JSON.toString(findFirst));
			final List<Record> data=Db.use("slave").find("select * from di_rule where ID > ? order by ID asc limit 500 ",findFirst.getInt("last_id"));
			if(null!=data&&data.size()>0) {
				Db.use("slave").tx(new IAtom() {
					@Override
					public boolean run() throws SQLException {
						return Db.use("master").tx(new IAtom() {
							@Override
							public boolean run() throws SQLException {
								Object lastInsertId = data.get(data.size() - 1).get("ID");
								Db.use("master").batchSave("di_rule", data, 500);
								findFirst.set("last_id", lastInsertId);
								findFirst.set("last_date", new Date());
								Db.use("slave").update("table_sync_info", findFirst);
								
								Record aa = Db.use("master").findFirst("select count(*) count from di_rule");
								return true;
							}
						});
					}
				});
				System.out.println("本阶段数据同步完成~~~");
			}else {
				flag=false;
			}
		}
	}
	
	
}
