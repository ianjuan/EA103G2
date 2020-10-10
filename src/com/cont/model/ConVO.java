package com.cont.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ConVO {

	private String con_no;
	private String apl_no;
	private String tnt_no;
	private String hos_no;
	private byte[] con_lld_sign;
	private Timestamp con_lld_signtime;
	private byte[] con_tnt_sign;
	private Timestamp con_tnt_signtime;
	private Integer con_out_normal;
	private Timestamp con_date;
	private Date con_che_date;
	private Integer hos_dep;
	private Date con_dep_bkdate;
	private Integer con_dep_sta;
	private Integer con_bill_paid;
	private Date con_lastb_pdate;
	private Date con_chkdate;
	private Integer con_comchkdate;
	private Integer con_chk_sta;
	private Integer con_chr_fee;
	private String con_chr_itm;
	private Integer con_is_chr;
	private Integer con_rent_agn;
	private Integer con_sta;

	public String getCon_no() {
		return con_no;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public String getApl_no() {
		return apl_no;
	}

	public void setApl_no(String apl_no) {
		this.apl_no = apl_no;
	}

	public String getTnt_no() {
		return tnt_no;
	}

	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}

	public String getHos_no() {
		return hos_no;
	}

	public void setHos_no(String hos_no) {
		this.hos_no = hos_no;
	}

	public byte[] getCon_lld_sign() {
		return con_lld_sign;
	}

	public void setCon_lld_sign(byte[] con_lld_sign) {
		this.con_lld_sign = con_lld_sign;
	}

	public Timestamp getCon_lld_signtime() {
		return con_lld_signtime;
	}

	public void setCon_lld_signtime(Timestamp con_lld_signtime) {
		this.con_lld_signtime = con_lld_signtime;
	}

	public byte[] getCon_tnt_sign() {
		return con_tnt_sign;
	}

	public void setCon_tnt_sign(byte[] con_tnt_sign) {
		this.con_tnt_sign = con_tnt_sign;
	}

	public Timestamp getCon_tnt_signtime() {
		return con_tnt_signtime;
	}

	public void setCon_tnt_signtime(Timestamp con_tnt_signtime) {
		this.con_tnt_signtime = con_tnt_signtime;
	}

	public Integer getCon_out_normal() {
		return con_out_normal;
	}

	public void setCon_out_normal(Integer con_out_normal) {
		this.con_out_normal = con_out_normal;
	}

	public Timestamp getCon_date() {
		return con_date;
	}

	public void setCon_date(Timestamp con_date) {
		this.con_date = con_date;
	}

	public Integer getHos_dep() {
		return hos_dep;
	}

	public void setHos_dep(Integer hos_dep) {
		this.hos_dep = hos_dep;
	}

	public Date getCon_che_date() {
		return con_che_date;
	}

	public void setCon_che_date(Date con_che_date) {
		this.con_che_date = con_che_date;
	}

	public Date getCon_dep_bkdate() {
		return con_dep_bkdate;
	}

	public void setCon_dep_bkdate(Date con_dep_bkdate) {
		this.con_dep_bkdate = con_dep_bkdate;
	}

	public Integer getCon_dep_sta() {
		return con_dep_sta;
	}

	public void setCon_dep_sta(Integer con_dep_sta) {
		this.con_dep_sta = con_dep_sta;
	}

	public Integer getCon_bill_paid() {
		return con_bill_paid;
	}

	public void setCon_bill_paid(Integer con_bill_paid) {
		this.con_bill_paid = con_bill_paid;
	}

	public Date getCon_lastb_pdate() {
		return con_lastb_pdate;
	}

	public void setCon_lastb_pdate(Date con_lastb_pdate) {
		this.con_lastb_pdate = con_lastb_pdate;
	}
	
	public Date getCon_chkdate() {
		return con_chkdate;
	}

	public void setCon_chkdate(Date con_chkdate) {
		this.con_chkdate = con_chkdate;
	}

	public Integer getCon_comchkdate() {
		return con_comchkdate;
	}

	public void setCon_comchkdate(Integer con_comchkdate) {
		this.con_comchkdate = con_comchkdate;
	}

	public Integer getCon_chk_sta() {
		return con_chk_sta;
	}

	public void setCon_chk_sta(Integer con_chk_sta) {
		this.con_chk_sta = con_chk_sta;
	}

	public Integer getCon_chr_fee() {
		return con_chr_fee;
	}

	public void setCon_chr_fee(Integer con_chr_fee) {
		this.con_chr_fee = con_chr_fee;
	}

	public String getCon_chr_itm() {
		return con_chr_itm;
	}

	public void setCon_chr_itm(String con_chr_itm) {
		this.con_chr_itm = con_chr_itm;
	}

	public Integer getCon_is_chr() {
		return con_is_chr;
	}

	public void setCon_is_chr(Integer con_is_chr) {
		this.con_is_chr = con_is_chr;
	}
	

	public Integer getCon_rent_agn() {
		return con_rent_agn;
	}

	public void setCon_rent_agn(Integer con_rent_agn) {
		this.con_rent_agn = con_rent_agn;
	}

	public Integer getCon_sta() {
		return con_sta;
	}

	public void setCon_sta(Integer con_sta) {
		this.con_sta = con_sta;
	}
}
