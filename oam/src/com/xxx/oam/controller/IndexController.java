package com.xxx.oam.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class IndexController extends Controller{
	public void index() {
		render("/html/index.html");
	}
}
