package com.cash.model;

import java.sql.Date;

public class CashVO implements java.io.Serializable {
	public static final String tntOut_YaJin = "tntOut_YaJin";
	public static final String tntOut_RecBill = "tntOut_RecBill";
	public static final String tntOut_Checkout = "tntOut_Checkout";
	public static final String tntOut_Withdraw = "tntOut_Withdraw";
	public static final String tntIn_YaJinReturn = "tntIn_YaJinReturn";
	public static final String tntIn_Deposit = "tntIn_Deposit";
	
	public static final String lldOut_YaJinReturn = "lldOut_YaJinReturn";
	public static final String lldOut_Withdraw = "lldOut_Withdraw";
	public static final String lldOut_publish = "lldOut_publish";
	public static final String lldIn_YaJin = "lldIn_YaJin";
	public static final String lldIn_RecBill = "lldIn_RecBill";
	public static final String lldIn_Checkout = "lldIn_Checkout";
	public static final String lldIn_Deposit = "lldIn_Deposit";
	
	public static final String cashIn = "in";
	public static final String cashOut = "out";
	
	private String cash_no;
	private Date cash_date;
	private String mem_no;
	private String cash_inout;
	private String cash_type;
	private Integer cash_amout;
	private String con_no;
	private String rec_no;
	
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

	public Integer getCash_amout() {
		return cash_amout;
	}

	public void setCash_amout(Integer cash_amout) {
		this.cash_amout = cash_amout;
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
