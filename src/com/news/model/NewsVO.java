package com.news.model;

public class NewsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String new_no;
	private String new_content;
	private Byte[] new_blob;
	private String emp_no;
	public String getNew_no() {
		return new_no;
	}
	public void setNew_no(String new_no) {
		this.new_no = new_no;
	}
	public String getNew_content() {
		return new_content;
	}
	public void setNew_content(String new_content) {
		this.new_content = new_content;
	}
	public Byte[] getNew_blob() {
		return new_blob;
	}
	public void setNew_blob(Byte[] new_blob) {
		this.new_blob = new_blob;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
}
