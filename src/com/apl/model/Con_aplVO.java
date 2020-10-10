package com.apl.model;

import java.sql.Date;

public class Con_aplVO {
	private String apl_no;
	private String tnt_no;
	private String hos_no;
	private Date apl_str;
	private Date apl_end;
	private Date apl_time;
	private Integer apl_status;
	
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
	public Date getApl_str() {
		return apl_str;
	}
	public void setApl_str(Date apl_str) {
		this.apl_str = apl_str;
	}
	public Date getApl_end() {
		return apl_end;
	}
	public void setApl_end(Date apl_end) {
		this.apl_end = apl_end;
	}
	public Date getApl_time() {
		return apl_time;
	}
	public void setApl_time(Date apl_time) {
		this.apl_time = apl_time;
	}
	public Integer getApl_status() {
		return apl_status;
	}
	public void setApl_status(Integer apl_status) {
		this.apl_status = apl_status;
	}
}
