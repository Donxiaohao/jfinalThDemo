package com.xxx.oam.task;

import com.xxx.oam.util.DateUtil;

public class RemoveTask implements Runnable{

	@Override
	public void run() {
		System.out.println("测试定时程序"+DateUtil.getNowDate());
	}

}
