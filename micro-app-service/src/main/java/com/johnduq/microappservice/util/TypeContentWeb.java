package com.johnduq.microappservice.util;

public enum TypeContentWeb {

	APPLICATION_JSON("application/json");

	private String code;

	private TypeContentWeb(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
