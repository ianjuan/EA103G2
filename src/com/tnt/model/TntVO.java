package com.tnt.model;

import java.sql.Date;
import java.sql.Timestamp;

public class TntVO implements java.io.Serializable {

	// -------profile-------
	private String tnt_no;
	private String tnt_email;
	private String tnt_acc;
	private String tnt_pwd;
	private String tnt_id;
	private String tnt_name;
	private Date tnt_birth;
	private Boolean tnt_sex;
	private String tnt_mobile;
	private String tnt_city;
	private String tnt_dist;
	private String tnt_add;
	private byte[] tnt_pic;
	private Integer tnt_status;
	private Timestamp tnt_jointime;
	// -------pocket-------
	private int tnt_balance;
	// -------card/bank-------
	private String tnt_bank;
	private String tnt_bankbranch;
	private String tnt_bankacc;
	private String tnt_card;
	private String tnt_cardsvc;
	private Date tnt_carddue;
	// -------CMT-------
	private int tnt_cmt_starsum;
	private int tnt_cmt_count;
	// -------VRF-------
	private String emp_no;
	private byte[] tnt_id_picf;
	private byte[] tnt_id_picb;
	private byte[] tnt_id_pic2;
	private Timestamp tnt_id_uploadtime;
	private int tnt_id_isupload;
	private int tnt_id_result;
	private String tnt_id_disapprove;
	private Timestamp tnt_id_vrftime;
	// -------RPT&AUTH-------
	private Integer tnt_reported_count;
	private Integer tnt_auth_chat;
	private Integer tnt_auth_res;
	private Integer tnt_auth_cmt;
	private Integer tnt_auth_rpt;
	private Integer tnt_auth_live;
	
	public TntVO() {}
	
	public String getTnt_no() {
		return tnt_no;
	}
	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}
	public String getTnt_email() {
		return tnt_email;
	}
	public void setTnt_email(String tnt_email) {
		this.tnt_email = tnt_email;
	}
	public String getTnt_acc() {
		return tnt_acc;
	}
	public void setTnt_acc(String tnt_acc) {
		this.tnt_acc = tnt_acc;
	}
	public String getTnt_pwd() {
		return tnt_pwd;
	}
	public void setTnt_pwd(String tnt_pwd) {
		this.tnt_pwd = tnt_pwd;
	}
	public String getTnt_id() {
		return tnt_id;
	}
	public void setTnt_id(String tnt_id) {
		this.tnt_id = tnt_id;
	}
	public String getTnt_name() {
		return tnt_name;
	}
	public void setTnt_name(String tnt_name) {
		this.tnt_name = tnt_name;
	}
	public Date getTnt_birth() {
		return tnt_birth;
	}
	public void setTnt_birth(Date tnt_birth) {
		this.tnt_birth = tnt_birth;
	}
	public Boolean getTnt_sex() {
		return tnt_sex;
	}
	public void setTnt_sex(Boolean tnt_sex) {
		this.tnt_sex = tnt_sex;
	}
	public String getTnt_mobile() {
		return tnt_mobile;
	}
	public void setTnt_mobile(String tnt_mobile) {
		this.tnt_mobile = tnt_mobile;
	}
	public String getTnt_city() {
		return tnt_city;
	}
	public void setTnt_city(String tnt_city) {
		this.tnt_city = tnt_city;
	}
	public String getTnt_dist() {
		return tnt_dist;
	}
	public void setTnt_dist(String tnt_dist) {
		this.tnt_dist = tnt_dist;
	}
	public String getTnt_add() {
		return tnt_add;
	}
	public void setTnt_add(String tnt_add) {
		this.tnt_add = tnt_add;
	}
	public byte[] getTnt_pic() {
		return tnt_pic;
	}
	public void setTnt_pic(byte[] tnt_pic) {
		this.tnt_pic = tnt_pic;
	}
	public Integer getTnt_status() {
		return tnt_status;
	}
	public void setTnt_status(Integer tnt_status) {
		this.tnt_status = tnt_status;
	}
	public Timestamp getTnt_jointime() {
		return tnt_jointime;
	}
	public void setTnt_jointime(Timestamp tnt_jointime) {
		this.tnt_jointime = tnt_jointime;
	}
	public int getTnt_balance() {
		return tnt_balance;
	}
	public void setTnt_balance(int tnt_balance) {
		this.tnt_balance = tnt_balance;
	}
	public String getTnt_bank() {
		return tnt_bank;
	}
	public void setTnt_bank(String tnt_bank) {
		this.tnt_bank = tnt_bank;
	}
	public String getTnt_bankbranch() {
		return tnt_bankbranch;
	}
	public void setTnt_bankbranch(String tnt_bankbranch) {
		this.tnt_bankbranch = tnt_bankbranch;
	}
	public String getTnt_bankacc() {
		return tnt_bankacc;
	}
	public void setTnt_bankacc(String tnt_bankacc) {
		this.tnt_bankacc = tnt_bankacc;
	}
	public String getTnt_card() {
		return tnt_card;
	}
	public void setTnt_card(String tnt_card) {
		this.tnt_card = tnt_card;
	}
	public String getTnt_cardsvc() {
		return tnt_cardsvc;
	}
	public void setTnt_cardsvc(String tnt_cardsvc) {
		this.tnt_cardsvc = tnt_cardsvc;
	}
	public Date getTnt_carddue() {
		return tnt_carddue;
	}
	public void setTnt_carddue(Date tnt_carddue) {
		this.tnt_carddue = tnt_carddue;
	}
	public int getTnt_cmt_starsum() {
		return tnt_cmt_starsum;
	}
	public void setTnt_cmt_starsum(int tnt_cmt_starsum) {
		this.tnt_cmt_starsum = tnt_cmt_starsum;
	}
	public int getTnt_cmt_count() {
		return tnt_cmt_count;
	}
	public void setTnt_cmt_count(int tnt_cmt_count) {
		this.tnt_cmt_count = tnt_cmt_count;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public byte[] getTnt_id_picf() {
		return tnt_id_picf;
	}
	public void setTnt_id_picf(byte[] tnt_id_picf) {
		this.tnt_id_picf = tnt_id_picf;
	}
	public byte[] getTnt_id_picb() {
		return tnt_id_picb;
	}
	public void setTnt_id_picb(byte[] tnt_id_picb) {
		this.tnt_id_picb = tnt_id_picb;
	}
	public byte[] getTnt_id_pic2() {
		return tnt_id_pic2;
	}
	public void setTnt_id_pic2(byte[] tnt_id_pic2) {
		this.tnt_id_pic2 = tnt_id_pic2;
	}
	public Timestamp getTnt_id_uploadtime() {
		return tnt_id_uploadtime;
	}
	public void setTnt_id_uploadtime(Timestamp tnt_id_uploadtime) {
		this.tnt_id_uploadtime = tnt_id_uploadtime;
	}
	public int getTnt_id_isupload() {
		return tnt_id_isupload;
	}
	public void setTnt_id_isupload(int tnt_id_isupload) {
		this.tnt_id_isupload = tnt_id_isupload;
	}
	public int getTnt_id_result() {
		return tnt_id_result;
	}
	public void setTnt_id_result(int tnt_id_result) {
		this.tnt_id_result = tnt_id_result;
	}
	public String getTnt_id_disapprove() {
		return tnt_id_disapprove;
	}
	public void setTnt_id_disapprove(String tnt_id_disapprove) {
		this.tnt_id_disapprove = tnt_id_disapprove;
	}
	public Timestamp getTnt_id_vrftime() {
		return tnt_id_vrftime;
	}
	public void setTnt_id_vrftime(Timestamp tnt_id_vrftime) {
		this.tnt_id_vrftime = tnt_id_vrftime;
	}
	public Integer getTnt_reported_count() {
		return tnt_reported_count;
	}
	public void setTnt_reported_count(Integer tnt_reported_count) {
		this.tnt_reported_count = tnt_reported_count;
	}
	public Integer getTnt_auth_chat() {
		return tnt_auth_chat;
	}
	public void setTnt_auth_chat(Integer tnt_auth_chat) {
		this.tnt_auth_chat = tnt_auth_chat;
	}
	public Integer getTnt_auth_res() {
		return tnt_auth_res;
	}
	public void setTnt_auth_res(Integer tnt_auth_res) {
		this.tnt_auth_res = tnt_auth_res;
	}
	public Integer getTnt_auth_cmt() {
		return tnt_auth_cmt;
	}
	public void setTnt_auth_cmt(Integer tnt_auth_cmt) {
		this.tnt_auth_cmt = tnt_auth_cmt;
	}
	public Integer getTnt_auth_rpt() {
		return tnt_auth_rpt;
	}
	public void setTnt_auth_rpt(Integer tnt_auth_rpt) {
		this.tnt_auth_rpt = tnt_auth_rpt;
	}
	public Integer getTnt_auth_live() {
		return tnt_auth_live;
	}
	public void setTnt_auth_live(Integer tnt_auth_live) {
		this.tnt_auth_live = tnt_auth_live;
	}
	

}

