package com.rptl.model;

import java.sql.Timestamp;

public class RptlVO {
public RptlVO() {
		
	} //沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rptl_no;
	private String lld_no;
	private String tnt_no;
	private Timestamp  rptl_time;
	private String rptl_content;
	private String emp_no;
	private Timestamp rptl_done_time;
	private Integer rptl_status;
	private Integer rptl_result;
	private String rptl_note;
	public String getRptl_no() {
		return rptl_no;
	}
	public void setRptl_no(String rptl_no) {
		this.rptl_no = rptl_no;
	}
	public String getLld_no() {
		return lld_no;
	}
	public void setLld_no(String lld_no) {
		this.lld_no = lld_no;
	}
	public String getTnt_no() {
		return tnt_no;
	}
	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}
	public Timestamp getRptl_time() {
		return rptl_time;
	}
	public void setRptl_time(Timestamp rptl_time) {
		this.rptl_time = rptl_time;
	}
	public String getRptl_content() {
		return rptl_content;
	}
	public void setRptl_content(String rptl_content) {
		this.rptl_content = rptl_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRptl_done_time() {
		return rptl_done_time;
	}
	public void setRptl_done_time(Timestamp rptl_done_time) {
		this.rptl_done_time = rptl_done_time;
	}
	public Integer getRptl_status() {
		return rptl_status;
	}
	public void setRptl_status(Integer rptl_status) {
		this.rptl_status = rptl_status;
	}
	public Integer getRptl_result() {
		return rptl_result;
	}
	public void setRptl_result(Integer rptl_result) {
		this.rptl_result = rptl_result;
	}
	public String getRptl_note() {
		return rptl_note;
	}
	public void setRptl_note(String rptl_note) {
		this.rptl_note = rptl_note;
	}
	
}
