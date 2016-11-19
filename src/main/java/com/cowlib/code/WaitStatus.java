package com.cowlib.code;

public enum WaitStatus {
	대기함("WAIT"),
	책빌림("DONE"),
	거절당함("REJECT"),
	취소함("CANCEL");

	private String code;

	WaitStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
