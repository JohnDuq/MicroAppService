package com.johnduq.microappservice.util;

public enum DateFormatValues {

	COMPLETE_DATE("yyyy/MM/dd HH:mm:ss.SSS");

	private String code;

	private DateFormatValues(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
