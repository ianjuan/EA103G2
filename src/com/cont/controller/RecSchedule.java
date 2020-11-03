package com.cont.controller;

import java.util.TimerTask;

import com.notify.controller.NotifyServlet;
import com.rec.model.RecService;

public class RecSchedule extends TimerTask {
	
	private String con_no = null;
	private String hos_no = null;
	private Integer rec_mon = 0;
	private Integer rec_sta = 0;
	private Integer count = 1;
	private String userNo = null;
	private String title = "填寫帳單時間到囉！";
	private String content = "請房東填寫定期帳單";
	private String url = "/EA103G2/front-end/contract/lldlistcontract.jsp";
	
	public RecSchedule(String con_no, String hos_no, Integer rec_mon, Integer rec_sta, String userNo) {
		super();
		this.con_no = con_no;
		this.hos_no = hos_no;
		this.rec_mon = rec_mon;
		this.rec_sta = rec_sta;
		this.userNo = userNo;
	}
	
	@Override
	public void run() {
		
		if (rec_mon >12) {
			rec_mon = 1;
		}
		if (count > 12) {
			return;
		}
		RecService recService = new RecService();
		recService.autorec(con_no, hos_no, rec_mon, rec_sta);
		rec_mon++;
		count++;
		
		new NotifyServlet().broadcast(userNo, title, content, url);
	}
}
