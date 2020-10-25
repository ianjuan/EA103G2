package com.news.model;

public class NewsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String new_no;
	private String new_content;
	private String new_date;
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
	public String getNew_date() {
		return new_date;
	}
	public void setNew_date(String new_date) {
		this.new_date = new_date;
	}

	
}
