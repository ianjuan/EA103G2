package com.rpthc.model;

import java.sql.Timestamp;

public class RpthcVO {
public RpthcVO() {
		
	} //沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rpthc_no;
	private String hcm_no;
	private String lld_no;
	private Timestamp  rpthc_time;
	private String rpthc_content;
	private String emp_no;
	private Timestamp rpthc_done_time;
	private Integer rpthc_status;
	private Integer rpthc_result;
	private String rpthc_note;
	
	
	public String getRpthc_no() {
		return rpthc_no;
	}
	public void setRpthc_no(String rpthc_no) {
		this.rpthc_no = rpthc_no;
	}
	public String getHcm_no() {
		return hcm_no;
	}
	public void setHcm_no(String hcm_no) {
		this.hcm_no = hcm_no;
	}
	public String getLld_no() {
		return lld_no;
	}
	public void setLld_no(String lld_no) {
		this.lld_no = lld_no;
	}
	public Timestamp getRpthc_time() {
		return rpthc_time;
	}
	public void setRpthc_time(Timestamp rpthc_time) {
		this.rpthc_time = rpthc_time;
	}
	public String getRpthc_content() {
		return rpthc_content;
	}
	public void setRpthc_content(String rpthc_content) {
		this.rpthc_content = rpthc_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRpthc_done_time() {
		return rpthc_done_time;
	}
	public void setRpthc_done_time(Timestamp rpthc_done_time) {
		this.rpthc_done_time = rpthc_done_time;
	}
	public Integer getRpthc_status() {
		return rpthc_status;
	}
	public void setRpthc_status(Integer rpthc_status) {
		this.rpthc_status = rpthc_status;
	}
	public Integer getRpthc_result() {
		return rpthc_result;
	}
	public void setRpthc_result(Integer rpthc_result) {
		this.rpthc_result = rpthc_result;
	}
	public String getRpthc_note() {
		return rpthc_note;
	}
	public void setRpthc_note(String rpthc_note) {
		this.rpthc_note = rpthc_note;
	}
}
