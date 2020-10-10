package com.apl.model;

public enum Con_aplStatus {
	Pending(0, "已申請"), Accecpt(1, "已接受"), Reject(2, "已拒絕");

	private Integer num;
	private String text;

	private Con_aplStatus(Integer num, String text) {
		this.num = num;
		this.text = text;
	}

	public Integer getNum() {
		return this.num;
	}

	public String getText() {
		return this.text;
	}

	public static Con_aplStatus findByPrimaryKey(Integer num) {
		for (Con_aplStatus status : values()) {
			if (status.getNum().equals(num))
				return status;
		}
		return null;
	}
}
