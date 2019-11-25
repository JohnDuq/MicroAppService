package com.johnduq.microappservice.api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthrityMixin {

	@JsonCreator
	public SimpleGrantedAuthrityMixin(@JsonProperty("authority") String role) {
	}

}
