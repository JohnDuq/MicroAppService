package com.johnduq.microappservice.util;

public enum TypeMessage {

	SUCCESS(2000, "Success"), INFO(2000, "Info"), WARN(3000, "Warn"), ERROR(4000, "Error");

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
