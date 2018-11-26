package com.xxx.oam.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.xxx.oam.entity.TopoLineInfo;
import com.xxx.oam.entity.TopoNodeInfo;

public class IndexController extends Controller{
	public void index() {
		List<TopoNodeInfo> topoNodeInfos = TopoNodeInfo.dao.find("select topo_node_info.*,topo_img.name from topo_node_info left join topo_img on topo_node_info.img_id=topo_img.id");
		setAttr("topoNodeInfos", topoNodeInfos);
		
		List<TopoLineInfo> topoLineInfos = TopoLineInfo.dao.find("select * from topo_line_info");
		setAttr("topoLineInfos",topoLineInfos);
		render("/html/jtopo3.html");
	}
	
	public void link() {
		render("/html/jtopo2.html");
	}
	
	public void testAjax() {
		renderNull();
	}
	
	public void testTx() {
		System.out.println("开始测试事务");
		List<Record> records=new ArrayList<Record>();
		
		Db.tx(new IAtom() {
			   public boolean run() throws SQLException {
			    //子库事务
			     boolean tx = Db.use("release").tx(new IAtom() {
			         public boolean run() throws SQLException {
			           try {
			              //写入子库
			        	   Record data = new Record().set("name","ttttt");
			               Db.use("release").save("test_out", data);
			               //更新主库记录
			              //操作主库, 如果失败, 子库和主库 都失败了
			              // int a=1/0;
						   Db.update("update test_in set name = ? where id=?", "hhh",5);
			               return true;
			           } catch (Exception e) {
			              //异常处理...
			        	   e.printStackTrace();
			              return false;
			           }
			         }
			      });
			     return tx;
			   }
			});

		renderNull();
	}
}
