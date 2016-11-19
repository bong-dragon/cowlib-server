package com.cowlib.code;

public enum BorrowStatus {
	빌려줌("BORROW"),
	반납함("HOME");

	private String code;

	BorrowStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
