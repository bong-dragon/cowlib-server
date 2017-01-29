package com.cowlib.jpa.model;

public enum RecordStatus {

	예약함("RESERVE"),
	거절당함("REJECT_RESERVE"),
	예약취소함("CANCEL_RESERVE"),

	빌려줌("BORROW"),
	빌려줌취소("CANCEL_BORROW"),

	반납함("RETURN");

	private String code;

	RecordStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
