package com.xxx.oam.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.xxx.oam.entity.TopoLineInfo;
import com.xxx.oam.entity.TopoNodeInfo;

public class IndexController extends Controller{
	public void index() {
		List<TopoNodeInfo> topoNodeInfos = TopoNodeInfo.dao.find("select topo_node_info.*,topo_img.name from topo_node_info left join topo_img on topo_node_info.img_id=topo_img.id");
		setAttr("topoNodeInfos", topoNodeInfos);
		
		List<TopoLineInfo> topoLineInfos = TopoLineInfo.dao.find("select * from topo_line_info");
		setAttr("topoLineInfos",topoLineInfos);
		render("/html/jtopo.html");
	}
	
	public void link() {
		render("/html/jtopo2.html");
	}
}
