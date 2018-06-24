package com.xxx.oam.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogTimerTask extends TimerTask {
	private Timer timer;
	
	public LogTimerTask(Timer timer){
		this.timer = timer;
	}
	@Override
	public void run() {
		System.out.println("定时任务开始"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		restart();
	}
	
	public void restart(){
		timer.schedule(new LogTimerTask(timer), 2000);
	}
	
}
