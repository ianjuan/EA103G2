package com.housemanage.model;

import java.sql.Date;
import java.util.TimerTask;

public class HouseSchdule extends TimerTask{
	private String hos_no = null;
	private Date hos_date = null;
	
	public HouseSchdule(String hos_no, Date hos_date) {
		super();
		this.hos_no = hos_no;
		this.hos_date = hos_date;
	}
		
	@Override
	public void run() {
				
	}
}
