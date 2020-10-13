package com.rptr.model;

import java.sql.Timestamp;

public class RptrVO {
public RptrVO() {
		
	} //沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rptr_no;
	private String rep_no;
	private String tnt_no;
	private Timestamp  rptr_time;
	private String rptr_content;
	private String emp_no;
	private Timestamp rptr_done_time;
	private Integer rptr_status;
	private Integer rptr_result;
	private String rptr_note;
	
	
	public String getRptr_no() {
		return rptr_no;
	}
	public void setRptr_no(String rptr_no) {
		this.rptr_no = rptr_no;
	}
	public String getRep_no() {
		return rep_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}
	public String getTnt_no() {
		return tnt_no;
	}
	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}
	public Timestamp getRptr_time() {
		return rptr_time;
	}
	public void setRptr_time(Timestamp rptr_time) {
		this.rptr_time = rptr_time;
	}
	public String getRptr_content() {
		return rptr_content;
	}
	public void setRptr_content(String rptr_content) {
		this.rptr_content = rptr_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRptr_done_time() {
		return rptr_done_time;
	}
	public void setRptr_done_time(Timestamp rptr_done_time) {
		this.rptr_done_time = rptr_done_time;
	}
	public Integer getRptr_status() {
		return rptr_status;
	}
	public void setRptr_status(Integer rptr_status) {
		this.rptr_status = rptr_status;
	}
	public Integer getRptr_result() {
		return rptr_result;
	}
	public void setRptr_result(Integer rptr_result) {
		this.rptr_result = rptr_result;
	}
	public String getRptr_note() {
		return rptr_note;
	}
	public void setRptr_note(String rptr_note) {
		this.rptr_note = rptr_note;
	}
}
