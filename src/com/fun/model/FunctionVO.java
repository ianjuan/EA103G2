package com.fun.model;

public class FunctionVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String fun_no;
	private String fun_name;
	public String getFun_name() {
		return fun_name;
	}
	public void setFun_name(String fun_name) {
		this.fun_name = fun_name;
	}
	public String getFun_no() {
		return fun_no;
	}
	public void setFun_no(String fun_no) {
		this.fun_no = fun_no;
	}

}
