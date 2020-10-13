package com.rpth.model;

import java.sql.Timestamp;

public class RpthVO {
	public RpthVO() {
} //沒有寫沒有無帶參數的建構子，系統也會自動產生


	private String rpth_no;
	private String tnt_no;
	private String hos_no;
	private Timestamp  rpth_time;
	private String rpth_content;
	private String emp_no;
	private Timestamp rpth_done_time;
	private Integer rpth_status;
	private Integer rpth_result;
	private String rpth_note;
	
	
	public String getRpth_no() {
		return rpth_no;
	}
	public void setRpth_no(String rpth_no) {
		this.rpth_no = rpth_no;
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
	public Timestamp getRpth_time() {
		return rpth_time;
	}
	public void setRpth_time(Timestamp rpth_time) {
		this.rpth_time = rpth_time;
	}
	public String getRpth_content() {
		return rpth_content;
	}
	public void setRpth_content(String rpth_content) {
		this.rpth_content = rpth_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRpth_done_time() {
		return rpth_done_time;
	}
	public void setRpth_done_time(Timestamp rpth_done_time) {
		this.rpth_done_time = rpth_done_time;
	}
	public Integer getRpth_status() {
		return rpth_status;
	}
	public void setRpth_status(Integer rpth_status) {
		this.rpth_status = rpth_status;
	}
	public Integer getRpth_result() {
		return rpth_result;
	}
	public void setRpth_result(Integer rpth_result) {
		this.rpth_result = rpth_result;
	}
	public String getRpth_note() {
		return rpth_note;
	}
	public void setRpth_note(String rpth_note) {
		this.rpth_note = rpth_note;
	}

}
