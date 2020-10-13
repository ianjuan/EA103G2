package com.cts.model;

import java.sql.Timestamp;

public class CtsVO {
	public CtsVO() {
		
	}//沒有寫沒有無帶參數的建構子，系統也會自動產生
	
	private String cts_no;
    private Integer cts_type;
    private String cts_question;
    private String cts_answer;
    private String emp_no;
    private Timestamp cts_time;
    private Integer cts_show;
	
     public String getCts_no() {
		return cts_no;
	}
	public void setCts_no(String cts_no) {
		this.cts_no = cts_no;
	}
	public Integer getCts_type() {
		return cts_type;
	}
	public void setCts_type(Integer cts_type) {
		this.cts_type = cts_type;
	}
	public String getCts_question() {
		return cts_question;
	}
	public void setCts_question(String cts_question) {
		this.cts_question = cts_question;
	}
	public String getCts_answer() {
		return cts_answer;
	}
	public void setCts_answer(String cts_answer) {
		this.cts_answer = cts_answer;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getCts_time() {
		return cts_time;
	}
	public void setCts_time(Timestamp cts_time) {
		this.cts_time = cts_time;
	}
	public Integer getCts_show() {
		return cts_show;
	}
	public void setCts_show(Integer cts_show) {
		this.cts_show = cts_show;
	}
}
