package com.johnduq.microappservice.util;

public enum TypeMessage {

	SUCCESS(2000, "Success"), INFO(3000, "Info"), WARN(4000, "Warn"), ERROR(5000, "Error");

	private int code;
	private String name;

	TypeMessage(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
