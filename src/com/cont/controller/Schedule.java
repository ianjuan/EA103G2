package com.cont.controller;

import java.util.TimerTask;

import com.cont.model.ConService;

public class Schedule extends TimerTask {
	
	private String con_no = null;
	private Integer con_sta = 0;
	
	public Schedule(String con_no, Integer con_sta) {
		super();
		this.con_no = con_no;
		this.con_sta = con_sta;
	}
	
	@Override
	public void run() {
		
		ConService conService = new ConService();
		conService.updatesta(con_sta, con_no);

	}
}
