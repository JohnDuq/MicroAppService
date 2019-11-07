package com.johnduq.microappservice.util;

public enum TypeDateFormat {

	COMPLETE_DATE("yyyy/MM/dd HH:mm:ss.SSS");

	private String code;

	private TypeDateFormat(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
