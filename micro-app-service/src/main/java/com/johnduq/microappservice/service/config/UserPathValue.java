package com.johnduq.microappservice.service.config;

public class UserPathValue {

	private UserPathValue() {
	}

	public static final String USER = "/user";
	public static final String USER_ID = USER + "/{id}";
	public static final String USER_USERNAME = USER + "/{username}/findByUsername";

}
