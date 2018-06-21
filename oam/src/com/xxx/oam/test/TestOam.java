package com.xxx.oam.test;

import com.jfinal.core.JFinal;

public class TestOam {
	public static void main(String[] args) {
		//启动项目 进行测试
		JFinal.start("WebRoot", 8989, "/",5);
	}
}
