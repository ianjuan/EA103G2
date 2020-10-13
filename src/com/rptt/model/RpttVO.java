package com.rptt.model;
import java.sql.Timestamp;

public class RpttVO implements java.io.Serializable{
	
	public RpttVO() {
		
	} //沒有寫沒有無帶參數的建構子，系統也會自動產生

	private String rptt_no;
	private String tnt_no;
	private String lld_no;
	private Timestamp  rptt_time;
	private String rptt_content;
	private String emp_no;
	private Timestamp rptt_done_time;
	private Integer rptt_status;
	private Integer rptt_result;
	private String rptt_note;
	
	
	public String getRptt_no() {
		return rptt_no;
	}
	public void setRptt_no(String rptt_no) {
		this.rptt_no = rptt_no;
	}
	public String getTnt_no() {
		return tnt_no;
	}
	public void setTnt_no(String tnt_no) {
		this.tnt_no = tnt_no;
	}
	public String getLld_no() {
		return lld_no;
	}
	public void setLld_no(String lld_no) {
		this.lld_no = lld_no;
	}
	public Timestamp getRptt_time() {
		return rptt_time;
	}
	public void setRptt_time(Timestamp rptt_time) {
		this.rptt_time = rptt_time;
	}
	public String getRptt_content() {
		return rptt_content;
	}
	public void setRptt_content(String rptt_content) {
		this.rptt_content = rptt_content;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Timestamp getRptt_done_time() {
		return rptt_done_time;
	}
	public void setRptt_done_time(Timestamp rptt_done_time) {
		this.rptt_done_time = rptt_done_time;
	}
	public Integer getRptt_status() {
		return rptt_status;
	}
	public void setRptt_status(Integer rptt_status) {
		this.rptt_status = rptt_status;
	}
	public Integer getRptt_result() {
		return rptt_result;
	}
	public void setRptt_result(Integer rptt_result) {
		this.rptt_result = rptt_result;
	}
	public String getRptt_note() {
		return rptt_note;
	}
	public void setRptt_note(String rptt_note) {
		this.rptt_note = rptt_note;
	}
	
}


