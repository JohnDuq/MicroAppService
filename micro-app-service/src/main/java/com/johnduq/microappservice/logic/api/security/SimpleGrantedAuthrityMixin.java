package com.johnduq.microappservice.logic.api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthrityMixin {

	@JsonCreator
	public SimpleGrantedAuthrityMixin(@JsonProperty("authority") String role) {
	}

}
