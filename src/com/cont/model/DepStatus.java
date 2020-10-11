package com.cont.model;

public enum DepStatus {
	Unpaid(0, "未繳交"), Paid(1, "已繳交"), Depo(2, "已抵押"), Back(3, "已退還"), Discon(4, "租約已解除");

	private Integer num;
	private String text;

	private DepStatus(Integer num, String text) {
		this.num = num;
		this.text = text;
	}

	public Integer getNum() {
		return this.num;
	}

	public String getText() {
		return this.text;
	}

	public static DepStatus findByPrimaryKey(Integer num) {
		for (DepStatus status : values()) {
			if (status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
