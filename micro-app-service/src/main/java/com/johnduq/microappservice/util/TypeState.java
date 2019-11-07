package com.johnduq.microappservice.util;

public enum TypeState {

	ENABLE("ENABLE"), DISABLE("DISABLE");

	private String code;

	private TypeState(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
