package com.johnduq.microappservice.util;

public enum TypeAuthValues {

	AUTHORIZATION("authorization"), AUTHORITIES("authorities"), BEARER("Bearer"), TOKEN("token"), USER("user");

	private String code;

	private TypeAuthValues(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
