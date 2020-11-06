package com.cash.model;

import java.sql.Date;

public class CashVO implements java.io.Serializable {
	public static final String tntOut_YaJin = "押金";
	public static final String tntOut_RecBill = "每月帳單";
	public static final String tntOut_Checkout = "退房帳單";
	public static final String tntOut_Withdraw = "提領";
	public static final String tntIn_YaJinReturn = "退回押金";
	public static final String tntIn_Deposit = "儲值";
	
	public static final String lldOut_YaJinReturn = "退回押金";
	public static final String lldOut_Withdraw = "提領";
	public static final String lldOut_publish = "上架費";
	public static final String lldIn_YaJin = "押金";
	public static final String lldIn_RecBill = "每月帳單";
	public static final String lldIn_Checkout = "退房帳單";
	public static final String lldIn_Deposit = "儲值";
	
	public static final String cashIn = "in";
	public static final String cashOut = "out";
	
	private String cash_no;
	private Date cash_date;
	private String mem_no;
	private String cash_inout;
	private String cash_type;
	private Integer cash_amount;
	private String con_no;
	private String rec_no;
	private Integer cash_status;
	
	public CashVO() {}

	public String getCash_no() {
		return cash_no;
	}

	public void setCash_no(String cash_no) {
		this.cash_no = cash_no;
	}

	public Date getCash_date() {
		return cash_date;
	}

	public void setCash_date(Date cash_date) {
		this.cash_date = cash_date;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getCash_inout() {
		return cash_inout;
	}

	public void setCash_inout(String cash_inout) {
		this.cash_inout = cash_inout;
	}

	public String getCash_type() {
		return cash_type;
	}

	public void setCash_type(String cash_type) {
		this.cash_type = cash_type;
	}

	public Integer getCash_amount() {
		return cash_amount;
	}

	public void setCash_amount(Integer cash_amount) {
		this.cash_amount = cash_amount;
	}

	public String getCon_no() {
		return con_no;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public String getRec_no() {
		return rec_no;
	}

	public void setRec_no(String rec_no) {
		this.rec_no = rec_no;
	}

	public Integer getCash_status() {
		return cash_status;
	}

	public void setCash_status(Integer cash_status) {
		this.cash_status = cash_status;
	}

	public static String getTntoutYajin() {
		return tntOut_YaJin;
	}

	public static String getTntoutRecbill() {
		return tntOut_RecBill;
	}

	public static String getTntoutCheckout() {
		return tntOut_Checkout;
	}

	public static String getTntoutWithdraw() {
		return tntOut_Withdraw;
	}

	public static String getTntinYajinreturn() {
		return tntIn_YaJinReturn;
	}

	public static String getTntinDeposit() {
		return tntIn_Deposit;
	}

	public static String getLldoutYajinreturn() {
		return lldOut_YaJinReturn;
	}

	public static String getLldoutWithdraw() {
		return lldOut_Withdraw;
	}

	public static String getLldoutPublish() {
		return lldOut_publish;
	}

	public static String getLldinYajin() {
		return lldIn_YaJin;
	}

	public static String getLldinRecbill() {
		return lldIn_RecBill;
	}

	public static String getLldinCheckout() {
		return lldIn_Checkout;
	}

	public static String getLldinDeposit() {
		return lldIn_Deposit;
	}

	public static String getCashin() {
		return cashIn;
	}

	public static String getCashout() {
		return cashOut;
	}
	
}
