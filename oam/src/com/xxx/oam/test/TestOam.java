package com.xxx.oam.test;

import com.jfinal.core.JFinal;

public class TestOam {
	public static void main(String[] args) {
		System.out.println("项目要启动啦~~~~");
		//启动项目 进行测试
		JFinal.start("WebRoot", 8989, "/",5);
	}
}
