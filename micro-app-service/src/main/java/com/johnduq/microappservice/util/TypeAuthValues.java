package com.johnduq.microappservice.util;

public enum TypeAuthValues {

	AUTHORIZATION("authorization"), AUTHORITIES("authorities"), AUTHORITY("authority"), BEARER("Bearer"), BEARER_("Bearer "), TOKEN("token"), USER("user");

	private String code;

	private TypeAuthValues(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
