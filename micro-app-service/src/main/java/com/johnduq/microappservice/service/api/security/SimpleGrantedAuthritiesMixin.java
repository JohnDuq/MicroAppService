package com.johnduq.microappservice.service.api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthritiesMixin {

	@JsonCreator
	public SimpleGrantedAuthritiesMixin(@JsonProperty("authority") String role) {
	}

}
