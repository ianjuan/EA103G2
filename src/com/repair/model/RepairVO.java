package com.repair.model;

import java.sql.Date;

public class RepairVO implements java.io.Serializable{
	//from table REPAIR
	private String rep_no;
	private String con_no;
	private String rep_dam_obj;
	private String rep_dam_obj_des;
	private Integer rep_pro;
	private Date rep_est_enddate;
	private Date rep_case_str;
	private Date rep_tnt_rpttime;
	private Integer rep_tnt_rpt;
	private Date rep_end_time;
	
	public String getRep_no() {
		return rep_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}
	public String getCon_no() {
		return con_no;
	}
	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}
	public String getRep_dam_obj() {
		return rep_dam_obj;
	}
	public void setRep_dam_obj(String rep_dam_obj) {
		this.rep_dam_obj = rep_dam_obj;
	}
	
	public String getRep_dam_obj_des() {
		return rep_dam_obj_des;
	}
	public void setRep_dam_obj_des(String rep_dam_obj_des) {
		this.rep_dam_obj_des = rep_dam_obj_des;
	}
	public Integer getRep_pro() {
		return rep_pro;
	}
	public void setRep_pro(Integer rep_pro) {
		this.rep_pro = rep_pro;
	}
	public Date getRep_est_enddate() {
		return rep_est_enddate;
	}
	public void setRep_est_enddate(Date rep_est_enddate) {
		this.rep_est_enddate = rep_est_enddate;
	}
	public Date getRep_case_str() {
		return rep_case_str;
	}
	public void setRep_case_str(Date rep_case_str) {
		this.rep_case_str = rep_case_str;
	}
	public Date getRep_tnt_rpttime() {
		return rep_tnt_rpttime;
	}
	public void setRep_tnt_rpttime(Date rep_tnt_rpttime) {
		this.rep_tnt_rpttime = rep_tnt_rpttime;
	}
	public Integer getRep_tnt_rpt() {
		return rep_tnt_rpt;
	}
	public void setRep_tnt_rpt(Integer rep_tnt_rpt) {
		this.rep_tnt_rpt = rep_tnt_rpt;
	}
	public Date getRep_end_time() {
		return rep_end_time;
	}
	public void setRep_end_time(Date rep_end_time) {
		this.rep_end_time = rep_end_time;
	}
	
}
