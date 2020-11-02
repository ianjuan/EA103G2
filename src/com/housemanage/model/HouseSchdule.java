package com.housemanage.model;

import java.util.TimerTask;

public class HouseSchdule extends TimerTask{
	private String hos_no = null;
	private String hos_status = null;
	
	public HouseSchdule(String hos_no, String hos_status) {
		super();
		this.hos_no = hos_no;
		this.hos_status = hos_status;
	}
		
	@Override
	public void run() {
		HouseService housesvc = new HouseService();
		housesvc.updateStatus(hos_status, hos_no);
	}
}
