package com.tenant_comments.model;

import java.sql.Date;


public class Tenant_commentsVO {
	
	 private String tcm_no;
	 private String lld_no; 
	 private String tnt_no;
	 private Integer tcm_clean;
	 private Integer tcm_commut;
	 private Integer tcm_satisfy;
	 private String tcm_commet;
	 private Date tcm_time;
	 private String tcm_respon;

	 
	public String getTcm_no() {
		return tcm_no;
	}
	public void setTcm_no(String tcm_no) {
		this.tcm_no = tcm_no;
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
	public Integer getTcm_clean() {
		return tcm_clean;
	}
	public void setTcm_clean(Integer tcm_clean) {
		this.tcm_clean = tcm_clean;
	}
	public Integer getTcm_commut() {
		return tcm_commut;
	}
	public void setTcm_commut(Integer tcm_commut) {
		this.tcm_commut = tcm_commut;
	}
	public Integer getTcm_satisfy() {
		return tcm_satisfy;
	}
	public void setTcm_satisfy(Integer tcm_satisfy) {
		this.tcm_satisfy = tcm_satisfy;
	}
	public String getTcm_commet() {
		return tcm_commet;
	}
	public void setTcm_commet(String tcm_commet) {
		this.tcm_commet = tcm_commet;
	}
	public Date getTcm_time() {
		return tcm_time;
	}
	public void setTcm_time(Date tcm_time) {
		this.tcm_time = tcm_time;
	}
	public String getTcm_respon() {
		return tcm_respon;
	}
	public void setTcm_respon(String tcm_respon) {
		this.tcm_respon = tcm_respon;
	}

	 
}
