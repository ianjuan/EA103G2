package com.rec.model;

public enum RecStatus {
	Unfill(0, "房東未填寫"), Fillin(1, "房東已填寫"), Paid(2, "房客已繳交");
	
	private Integer num;
	private String text;
	
	private RecStatus(Integer num, String text) {
		this.num = num;
		this.text = text;
	}
	
	public Integer getNum() {
		return this.num;
	}
	
	public String getText() {
		return this.text;
	}
	
	public static RecStatus findByPrimaryKey(Integer num) {
		for (RecStatus status : values()) {
			if(status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
