package com.johnduq.microappservice.api.config;

public class UserPathValue {

	private UserPathValue() {
	}

	public static final String USER = "/user";
	public static final String USER_ID = USER + "/{id}";
	public static final String USER_USERNAME = USER + "/{username}/findByUsername";

}
