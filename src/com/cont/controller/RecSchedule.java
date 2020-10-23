package com.cont.controller;

import java.util.TimerTask;

import com.rec.model.RecService;

public class RecSchedule extends TimerTask {
	
	private String con_no = null;
	private String hos_no = null;
	private Integer rec_mon = 0;
	private Integer rec_sta = 0;
	
	public RecSchedule(String con_no, String hos_no, Integer rec_mon, Integer rec_sta) {
		super();
		this.con_no = con_no;
		this.hos_no = hos_no;
		this.rec_mon = rec_mon;
		this.rec_sta = rec_sta;
	}
	
	@Override
	public void run() {
		
		if (rec_mon >12) {
			rec_mon = 1;
		}
		RecService recService = new RecService();
		recService.autorec(con_no, hos_no, rec_mon, rec_sta);
		rec_mon++;
		
		
	}
}
