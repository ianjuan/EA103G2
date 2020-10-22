package com.cont.controller;

import java.util.Date;
import java.util.TimerTask;

public class DateTask extends TimerTask {
	@Override
	public void run() {
		System.out.println("Task 執行時間：" + new Date());

	}
}