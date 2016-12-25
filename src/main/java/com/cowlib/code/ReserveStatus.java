package com.cowlib.code;

public enum ReserveStatus {
	예약함("RESERVE"),
	책빌림("DONE"),
	거절당함("REJECT"),
	취소함("CANCEL");

	private String code;

	ReserveStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
