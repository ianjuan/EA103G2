package com.rptlc.model;

import java.sql.Timestamp;

public class RptlcVO {
public RptlcVO() {
		
	}//沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rptlc_no;
	private String lcm_no;
	private String lld_no;
	private Timestamp  rptlc_time;
	private String rptlc_content;
	private String emp_no;
	private Timestamp rptlc_done_time;
	private Integer rptlc_status;
	private Integer rptlc_result;
	private String rptlc_note;
	
	
	public String getRptlc_no() {
		return rptlc_no;
	}
	public void setRptlc_no(String rptlc_no) {
		this.rptlc_no = rptlc_no;
	}
	public String getLcm_no() {
		return lcm_no;
	}
	public void setLcm_no(String lcm_no) {
		this.lcm_no = lcm_no;
	}
	public String getLld_no() {
		return lld_no;
	}
	public void setLld_no(String lld_no) {
		this.lld_no = lld_no;
	}
	public Timestamp getRptlc_time() {
		return rptlc_time;
	}
	public void setRptlc_time(Timestamp rptlc_time) {
		this.rptlc_time = rptlc_time;
	}
	public String getRptlc_content() {
		return rptlc_content;
	}
	public void setRptlc_content(String rptlc_content) {
		this.rptlc_content = rptlc_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRptlc_done_time() {
		return rptlc_done_time;
	}
	public void setRptlc_done_time(Timestamp rptlc_done_time) {
		this.rptlc_done_time = rptlc_done_time;
	}
	public Integer getRptlc_status() {
		return rptlc_status;
	}
	public void setRptlc_status(Integer rptlc_status) {
		this.rptlc_status = rptlc_status;
	}
	public Integer getRptlc_result() {
		return rptlc_result;
	}
	public void setRptlc_result(Integer rptlc_result) {
		this.rptlc_result = rptlc_result;
	}
	public String getRptlc_note() {
		return rptlc_note;
	}
	public void setRptlc_note(String rptlc_note) {
		this.rptlc_note = rptlc_note;
	}
}
