package com.johnduq.microappservice.logic.api.auth;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

public interface IJWTApi {

	public String create(Authentication authentication) throws IOException;
	
	public boolean validate(String token);
	
	public Claims getClaims(String token);
	
	public String getUsername(String token);
	
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException ;
	
	public String resolve(String token);
	
}
