package com.xxx.oam.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller{
	public void index() {
		System.out.println("ttttttttttt");
		render("/html/index.html");
	}
}
