package com.xxx.oam.test;

import java.sql.SQLException;

import org.junit.Test;

import com.jfinal.aop.Before;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.druid.DruidPlugin;
public class TestTX {
	@Test
	public void testTX() {
		Prop p = PropKit.use("mysql_config.txt");
		DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"),
                p.get("user"),
                p.get("password"));
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dp);
		dp.start();
		arp.start();
		
		Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				for(int i=0;i<10;i++) {
					/*if(i==8) {
						throw new RuntimeException("测试");
					}*/
					Record set = new Record().set("name","testTx");
					Db.save("test_in", set);
				}
				return true;
			}
		});
	}
}
