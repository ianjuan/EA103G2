package com.lld.model;

import java.sql.Date;
import java.sql.Timestamp;

public class LldVO implements java.io.Serializable {

	// -------profile-------
	private String lld_no;
	private String lld_email;
	private String lld_acc;
	private String lld_pwd;
	private String lld_id;
	private String lld_name;
	private Date lld_birth;
	private Boolean lld_sex;
	private String lld_mobile;
	private String lld_city;
	private String lld_dist;
	private String lld_add;
	private byte[] lld_pic;
	private Integer lld_status;
	private Timestamp lld_jointime;
	// -------pocket-------
	private int lld_balance;
	// -------card/bank-------
	private String lld_bank;
	private String lld_bankbranch;
	private String lld_bankacc;
	private String lld_card;
	private String lld_cardsvc;
	private Date lld_carddue;
	// -------CMT-------
	private int lld_cmt_starsum;
	private int lld_cmt_count;
	// -------VRF-------
	private String emp_no;
	private byte[] lld_id_picf;
	private byte[] lld_id_picb;
	private byte[] lld_id_pic2;
	private Timestamp lld_id_uploadtime;
	private int lld_id_isupload;
	private int lld_id_result;
	private String lld_id_disapprove;
	private Timestamp lld_id_vrftime;
	// -------RPT&AUTH-------
	private Integer lld_reported_count;
	private Integer lld_auth_chat;
	private Integer lld_auth_res;
	private Integer lld_auth_cmt;
	private Integer lld_auth_rpt;
    private Integer lld_auth_hos;
    
    public LldVO() {}

	public String getLld_no() {
		return lld_no;
	}

	public void setLld_no(String lld_no) {
		this.lld_no = lld_no;
	}

	public String getLld_email() {
		return lld_email;
	}

	public void setLld_email(String lld_email) {
		this.lld_email = lld_email;
	}

	public String getLld_acc() {
		return lld_acc;
	}

	public void setLld_acc(String lld_acc) {
		this.lld_acc = lld_acc;
	}

	public String getLld_pwd() {
		return lld_pwd;
	}

	public void setLld_pwd(String lld_pwd) {
		this.lld_pwd = lld_pwd;
	}

	public String getLld_id() {
		return lld_id;
	}

	public void setLld_id(String lld_id) {
		this.lld_id = lld_id;
	}

	public String getLld_name() {
		return lld_name;
	}

	public void setLld_name(String lld_name) {
		this.lld_name = lld_name;
	}

	public Date getLld_birth() {
		return lld_birth;
	}

	public void setLld_birth(Date lld_birth) {
		this.lld_birth = lld_birth;
	}

	public Boolean getLld_sex() {
		return lld_sex;
	}

	public void setLld_sex(Boolean lld_sex) {
		this.lld_sex = lld_sex;
	}

	public String getLld_mobile() {
		return lld_mobile;
	}

	public void setLld_mobile(String lld_mobile) {
		this.lld_mobile = lld_mobile;
	}

	public String getLld_city() {
		return lld_city;
	}

	public void setLld_city(String lld_city) {
		this.lld_city = lld_city;
	}

	public String getLld_dist() {
		return lld_dist;
	}

	public void setLld_dist(String lld_dist) {
		this.lld_dist = lld_dist;
	}

	public String getLld_add() {
		return lld_add;
	}

	public void setLld_add(String lld_add) {
		this.lld_add = lld_add;
	}

	public byte[] getLld_pic() {
		return lld_pic;
	}

	public void setLld_pic(byte[] lld_pic) {
		this.lld_pic = lld_pic;
	}

	public Integer getLld_status() {
		return lld_status;
	}

	public void setLld_status(Integer lld_status) {
		this.lld_status = lld_status;
	}

	public Timestamp getLld_jointime() {
		return lld_jointime;
	}

	public void setLld_jointime(Timestamp lld_jointime) {
		this.lld_jointime = lld_jointime;
	}

	public int getLld_balance() {
		return lld_balance;
	}

	public void setLld_balance(int lld_balance) {
		this.lld_balance = lld_balance;
	}

	public String getLld_bank() {
		return lld_bank;
	}

	public void setLld_bank(String lld_bank) {
		this.lld_bank = lld_bank;
	}

	public String getLld_bankbranch() {
		return lld_bankbranch;
	}

	public void setLld_bankbranch(String lld_bankbranch) {
		this.lld_bankbranch = lld_bankbranch;
	}

	public String getLld_bankacc() {
		return lld_bankacc;
	}

	public void setLld_bankacc(String lld_bankacc) {
		this.lld_bankacc = lld_bankacc;
	}

	public String getLld_card() {
		return lld_card;
	}

	public void setLld_card(String lld_card) {
		this.lld_card = lld_card;
	}

	public String getLld_cardsvc() {
		return lld_cardsvc;
	}

	public void setLld_cardsvc(String lld_cardsvc) {
		this.lld_cardsvc = lld_cardsvc;
	}

	public Date getLld_carddue() {
		return lld_carddue;
	}

	public void setLld_carddue(Date lld_carddue) {
		this.lld_carddue = lld_carddue;
	}

	public int getLld_cmt_starsum() {
		return lld_cmt_starsum;
	}

	public void setLld_cmt_starsum(int lld_cmt_starsum) {
		this.lld_cmt_starsum = lld_cmt_starsum;
	}

	public int getLld_cmt_count() {
		return lld_cmt_count;
	}

	public void setLld_cmt_count(int lld_cmt_count) {
		this.lld_cmt_count = lld_cmt_count;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public byte[] getLld_id_picf() {
		return lld_id_picf;
	}

	public void setLld_id_picf(byte[] lld_id_picf) {
		this.lld_id_picf = lld_id_picf;
	}

	public byte[] getLld_id_picb() {
		return lld_id_picb;
	}

	public void setLld_id_picb(byte[] lld_id_picb) {
		this.lld_id_picb = lld_id_picb;
	}

	public byte[] getLld_id_pic2() {
		return lld_id_pic2;
	}

	public void setLld_id_pic2(byte[] lld_id_pic2) {
		this.lld_id_pic2 = lld_id_pic2;
	}

	public Timestamp getLld_id_uploadtime() {
		return lld_id_uploadtime;
	}

	public void setLld_id_uploadtime(Timestamp lld_id_uploadtime) {
		this.lld_id_uploadtime = lld_id_uploadtime;
	}

	public int getLld_id_isupload() {
		return lld_id_isupload;
	}

	public void setLld_id_isupload(int lld_id_isupload) {
		this.lld_id_isupload = lld_id_isupload;
	}

	public int getLld_id_result() {
		return lld_id_result;
	}

	public void setLld_id_result(int lld_id_result) {
		this.lld_id_result = lld_id_result;
	}

	public String getLld_id_disapprove() {
		return lld_id_disapprove;
	}

	public void setLld_id_disapprove(String lld_id_disapprove) {
		this.lld_id_disapprove = lld_id_disapprove;
	}

	public Timestamp getLld_id_vrftime() {
		return lld_id_vrftime;
	}

	public void setLld_id_vrftime(Timestamp lld_id_vrftime) {
		this.lld_id_vrftime = lld_id_vrftime;
	}

	public Integer getLld_reported_count() {
		return lld_reported_count;
	}

	public void setLld_reported_count(Integer lld_reported_count) {
		this.lld_reported_count = lld_reported_count;
	}

	public Integer getLld_auth_chat() {
		return lld_auth_chat;
	}

	public void setLld_auth_chat(Integer lld_auth_chat) {
		this.lld_auth_chat = lld_auth_chat;
	}

	public Integer getLld_auth_res() {
		return lld_auth_res;
	}

	public void setLld_auth_res(Integer lld_auth_res) {
		this.lld_auth_res = lld_auth_res;
	}

	public Integer getLld_auth_cmt() {
		return lld_auth_cmt;
	}

	public void setLld_auth_cmt(Integer lld_auth_cmt) {
		this.lld_auth_cmt = lld_auth_cmt;
	}

	public Integer getLld_auth_rpt() {
		return lld_auth_rpt;
	}

	public void setLld_auth_rpt(Integer lld_auth_rpt) {
		this.lld_auth_rpt = lld_auth_rpt;
	}

	public Integer getLld_auth_hos() {
		return lld_auth_hos;
	}

	public void setLld_auth_hos(Integer lld_auth_hos) {
		this.lld_auth_hos = lld_auth_hos;
	}

}
