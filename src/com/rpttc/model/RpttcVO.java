package com.rpttc.model;

import java.sql.Timestamp;

public class RpttcVO {
public RpttcVO() {
		
	} //沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rpttc_no;
	private String tcm_no;
	private String tnt_no;
	private Timestamp  rpttc_time;
	private String rpttc_content;
	private String emp_no;
	private Timestamp rpttc_done_time;
	private Integer rpttc_status;
	private Integer rpttc_result;
	private String rpttc_note;
	
	
	public String getRpttc_no() {
		return rpttc_no;
	}
	public void setRpttc_no(String rpttc_no) {
		this.rpttc_no = rpttc_no;
	}
	public String getTcm_no() {
		return tcm_no;
	}
	public void setTcm_no(String tcm_no) {
		this.tcm_no = tcm_no;
	}
	public String getTnt_no() {
		return tnt_no;
	}
	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}
	public Timestamp getRpttc_time() {
		return rpttc_time;
	}
	public void setRpttc_time(Timestamp rpttc_time) {
		this.rpttc_time = rpttc_time;
	}
	public String getRpttc_content() {
		return rpttc_content;
	}
	public void setRpttc_content(String rpttc_content) {
		this.rpttc_content = rpttc_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRpttc_done_time() {
		return rpttc_done_time;
	}
	public void setRpttc_done_time(Timestamp rpttc_done_time) {
		this.rpttc_done_time = rpttc_done_time;
	}
	public Integer getRpttc_status() {
		return rpttc_status;
	}
	public void setRpttc_status(Integer rpttc_status) {
		this.rpttc_status = rpttc_status;
	}
	public Integer getRpttc_result() {
		return rpttc_result;
	}
	public void setRpttc_result(Integer rpttc_result) {
		this.rpttc_result = rpttc_result;
	}
	public String getRpttc_note() {
		return rpttc_note;
	}
	public void setRpttc_note(String rpttc_note) {
		this.rpttc_note = rpttc_note;
	}
}
