package com.cont.model;

public enum NumEnum {
	Zero(0, "無"), One(1, "一個"), Two(2, "兩個"), Three(3, "三個"), Four(4, "四個"), Five(5, "五個"), Six(6, "六個"), Seven(7, "七個"), Eight(8, "八個"), Nine(9, "九個"), Ten(10, "10個");

	private Integer num;
	private String text;

	private NumEnum(Integer num, String text) {
		this.num = num;
		this.text = text;
	}

	public Integer getNum() {
		return this.num;
	}

	public String getText() {
		return this.text;
	}

	public static NumEnum findByPrimaryKey(Integer num) {
		for (NumEnum status : values()) {
			if (status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
