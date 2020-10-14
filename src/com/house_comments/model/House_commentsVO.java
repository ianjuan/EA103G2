package com.house_comments.model;

import java.sql.Date;
import java.sql.Time;

public class House_commentsVO {
	
 private String hcm_no;
 private String tnt_no; 
 private String hos_no;
 private Integer hcm_eqpmt;
 private Integer hcm_convmt;
 private Integer hcm_neibor;
 private String hcm_commnt;
 private Date hcm_time;
 private String hcm_respon;
 
 
public String getHcm_no() {
	return hcm_no;
}
public void setHcm_no(String hcm_no) {
	this.hcm_no = hcm_no;
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
public Integer getHcm_eqpmt() {
	return hcm_eqpmt;
}
public void setHcm_eqpmt(Integer hcm_eqpmt) {
	this.hcm_eqpmt = hcm_eqpmt;
}

public Integer getHcm_convmt() {
	return hcm_convmt;
}
public void setHcm_convmt(Integer hcm_convmt) {
	this.hcm_convmt = hcm_convmt;
}
public Integer getHcm_neibor() {
	return hcm_neibor;
}
public void setHcm_neibor(Integer hcm_neibor) {
	this.hcm_neibor = hcm_neibor;
}
public String getHcm_commnt() {
	return hcm_commnt;
}
public void setHcm_commnt(String hcm_commnt) {
	this.hcm_commnt = hcm_commnt;
}

public Date getHcm_time() {
	return hcm_time;
}
public void setHcm_time(Date hcm_time) {
	this.hcm_time = hcm_time;
}

public String getHcm_respon() {
	return hcm_respon;
}
public void setHcm_respon(String hcm_respon) {
	this.hcm_respon = hcm_respon;
}

 
}
