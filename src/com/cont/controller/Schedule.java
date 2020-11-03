package com.cont.controller;

import java.util.TimerTask;

import com.cont.model.ConService;
import com.notify.controller.NotifyServlet;

public class Schedule extends TimerTask {
	
	private String con_no = null;
	private Integer con_sta = 0;
	private String userNo = null;
	private String title = null;
	private String content = null;
	private String url = null;
	
	
	public Schedule(String con_no, Integer con_sta, String userNo, String title, String content, String url) {
		super();
		this.con_no = con_no;
		this.con_sta = con_sta;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.url = url;
	}
	
	@Override
	public void run() {
		
		ConService conService = new ConService();
		conService.updatesta(con_sta, con_no);
		
		new NotifyServlet().broadcast(userNo, title, content, url);

	}
}
