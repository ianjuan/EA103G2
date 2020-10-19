package com.cont.model;

public enum ConStatus {
	Lldunsign(0, "房東尚未填寫"), Tntunsign(1, "房客尚未填寫"), BeforeRent(2, "尚未入住"), Renting(3, "入住中"), Checkout(4, "已退房"), Fincon(5, "租約已完成"), Discon(6, "租約已解除");

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
