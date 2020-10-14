package com.repair_picture.model;

public class Repair_pictureVO {
	private String reppic_no;
	private String rep_no;
	private byte[] reppic_pic;
	private String path;
	
	public String getReppic_no() {
		return reppic_no;
	}
	public void setReppic_no(String reppic_no) {
		this.reppic_no = reppic_no;
	}
	public String getRep_no() {
		return rep_no;
	}
	public void setRep_no(String rep_no) {
		this.rep_no = rep_no;
	}
	public byte[] getReppic_pic() {
		return reppic_pic;
	}
	public void setReppic_pic(byte[] reppic_pic) {
		this.reppic_pic = reppic_pic;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
