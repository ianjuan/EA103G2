package com.cont.controller;

import java.util.TimerTask;

import com.cash.model.*;
import com.notify.controller.NotifyServlet;
import com.tnt.model.TntService;

public class CheckoutdepSchedule extends TimerTask{
	CashVO cashVO = new CashVO();
	
	private String con_no = null;
	private String cash_inout = "收入";
	private String mem_no = null;
	private String cash_type = "退回押金";
	private Integer cash_status = 1;
	private Integer cash_amount = 0;
	private String userNo = null;
	private String title = "押金已入帳";
	private String content = "請房客前往錢包查看";
	private String url = "/EA103G2/front-end/lld/bills.jsp";
	
	
	public CheckoutdepSchedule(String userNo, String con_no, String mem_no, Integer cash_amount) {
		super();
		
		this.userNo = userNo;
		this.con_no = con_no;
		this.mem_no = mem_no;
		this.cash_amount = cash_amount;
	}
	
	@Override
	public void run() {
		CashService cashService = new CashService();
		java.sql.Date cash_date = new java.sql.Date(new java.util.Date().getTime());
		
		cashService.addCash(cash_date, mem_no, cash_inout, cash_type, cash_amount, con_no, cash_status);
		
		new NotifyServlet().broadcast(userNo, title, content, url);
		
		String tnt_no = userNo;
		
		TntService tntService = new TntService();
		Integer tnt_blance = tntService.getOneTntPocket(tnt_no).getTnt_balance() + cash_amount;
		tntService.updateTntPocket(tnt_no, tnt_blance);
	}

}
