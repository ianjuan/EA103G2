package com.cont.controller;

import java.util.TimerTask;

import com.cash.model.*;

public class CheckoutdepSchedule extends TimerTask{
	CashVO cashVO = new CashVO();
	
	private String con_no = null;
	private String cash_inout = cashVO.cashIn;
	private String mem_no = null;
	private String cash_type = cashVO.tntIn_YaJinReturn;
	private Integer cash_status = 1;
	private Integer cash_amount = 0;
	
	
	public CheckoutdepSchedule(String con_no, String mem_no, Integer cash_amount) {
		super();
		this.con_no = con_no;
		this.mem_no = mem_no;
		this.cash_amount = cash_amount;
	}
	
	@Override
	public void run() {
		CashService cashService = new CashService();
		java.sql.Date cash_date = new java.sql.Date(new java.util.Date().getTime());
		cashService.addCash(cash_date, mem_no, cash_inout, cash_type, cash_amount, con_no, cash_status);
	}

}
