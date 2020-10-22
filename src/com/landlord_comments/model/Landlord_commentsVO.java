package com.landlord_comments.model;

import java.sql.Date;
import java.sql.Time;

public class Landlord_commentsVO {
	
	 private String lcm_no;
	 private String tnt_no; 
	 private String lld_no;
	 private Integer lcm_clean;
	 private Integer lcm_commut;
	 private Integer lcm_satisfy;
	 private String lcm_commet;
	 private Date lcm_time;
	 private String lcm_respon;
	 
	 
	 public String getLcm_no() {
		return lcm_no;
	}
	public void setLcm_no(String lcm_no) {
		this.lcm_no = lcm_no;
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
	public Integer getLcm_clean() {
		return lcm_clean;
	}
	public void setLcm_clean(Integer lcm_clean) {
		this.lcm_clean = lcm_clean;
	}
	public Integer getLcm_commut() {
		return lcm_commut;
	}
	public void setLcm_commut(Integer lcm_commut) {
		this.lcm_commut = lcm_commut;
	}
	public Integer getLcm_satisfy() {
		return lcm_satisfy;
	}
	public void setLcm_satisfy(Integer lcm_satisfy) {
		this.lcm_satisfy = lcm_satisfy;
	}
	public String getLcm_commet() {
		return lcm_commet;
	}
	public void setLcm_commet(String lcm_commet) {
		this.lcm_commet = lcm_commet;
	}
	public Date getLcm_time() {
		return lcm_time;
	}
	public void setLcm_time(Date lcm_time) {
		this.lcm_time = lcm_time;
	}
	public String getLcm_respon() {
		return lcm_respon;
	}
	public void setLcm_respon(String lcm_respon) {
		this.lcm_respon = lcm_respon;
	}
	
	
}
