package com.cont.model;

public enum ConStatus {
	BeforeRent(0, "尚未入住"), Renting(1, "入住中"), Checkout(2, "已退房"), Fincon(3, "租約已完成"), Discon(4, "租約已解除");

	private Integer num;
	private String text;

	private ConStatus(Integer num, String text) {
		this.num = num;
		this.text = text;
	}

	public Integer getNum() {
		return this.num;
	}

	public String getText() {
		return this.text;
	}

	public static ConStatus findByPrimaryKey(Integer num) {
		for (ConStatus status : values()) {
			if (status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
