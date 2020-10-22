package com.rec.model;

public enum Month {
	Jan(1, "一月"), Feb(2, "二月"), Mar(3, "三月"), Apr(4, "四月"), May(5, "五月"), Jun(6, "六月"), Jul(7, "七月"), Org(8, "八月"), Sep(9, "九月"), Oct(10, "十月"), Nov(11, "十一月"), Dec(12, "十二月");
	
	private Integer num;
	private String text;
	
	private Month(Integer num, String text) {
		this.num = num;
		this.text = text;
	}
	
	public Integer getNum() {
		return this.num;
	}
	
	public String getText() {
		return this.text;
	}
	
	public static Month findByPrimaryKey(Integer num) {
		for (Month status : values()) {
			if(status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
