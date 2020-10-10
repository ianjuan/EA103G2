package com.rec.model;

import java.sql.Timestamp;

public class RecVO implements java.io.Serializable{
	
	private String rec_no;
	private String con_no;
	private String hos_no;
	private Integer rec_mon;
	private Integer rec_water;
	private Integer rec_elec;
	private Timestamp rec_time;
	
	public String getRec_no() {
		return rec_no;
	}
	public void setRec_no(String rec_no) {
		this.rec_no = rec_no;
	}
	public String getCon_no() {
		return con_no;
	}
	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}
	public String getHos_no() {
		return hos_no;
	}
	public void setHos_no(String hos_no) {
		this.hos_no = hos_no;
	}
	public Integer getRec_mon() {
		return rec_mon;
	}
	public void setRec_mon(Integer rec_mon) {
		this.rec_mon = rec_mon;
	}
	public Integer getRec_water() {
		return rec_water;
	}
	public void setRec_water(Integer rec_water) {
		this.rec_water = rec_water;
	}
	public Integer getRec_elec() {
		return rec_elec;
	}
	public void setRec_elec(Integer rec_elec) {
		this.rec_elec = rec_elec;
	}
	public Timestamp getRec_time() {
		return rec_time;
	}
	public void setRec_time(Timestamp rec_time) {
		this.rec_time = rec_time;
	}
}
