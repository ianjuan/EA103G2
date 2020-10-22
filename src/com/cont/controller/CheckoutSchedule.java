package com.cont.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimerTask;

import com.cont.model.ConService;

public class CheckoutSchedule extends TimerTask{

	private Integer hos_dep = 0;
	private Integer con_dep_sta = 0;
	private Date con_chkdate = null;
	private Integer con_comchkdate = 0;
	private Integer con_chk_sta = 0;
	private Integer con_chr_fee = 0;
	private String con_chr_itm = null;
	private Integer con_is_chr = 0;
	private String con_no = null;
	
	public CheckoutSchedule(Integer hos_dep, Integer con_dep_sta, Date con_chkdate, Integer con_comchkdate, Integer con_chk_sta, Integer con_chr_fee, String con_chr_itm, String con_no, Integer con_is_chr) {
		
		super();
		
		this.hos_dep = hos_dep;
		this.con_dep_sta = con_dep_sta;
		this.con_chkdate = con_chkdate;
		this.con_chk_sta = con_chk_sta;
		this.con_chr_fee = con_chr_fee;
		this.con_chr_itm = con_chr_itm;
		this.con_is_chr = con_is_chr;
		this.con_no = con_no;
		
	}
	
	@Override
	public void run() {
		
		ConService conService = new ConService();
		conService.updatebeforecheckout(hos_dep, con_dep_sta, con_chkdate, con_comchkdate, con_chk_sta, con_chr_fee, con_chr_itm, con_is_chr, con_no);
	}
}
