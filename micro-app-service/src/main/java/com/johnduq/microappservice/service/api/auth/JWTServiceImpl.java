package com.johnduq.microappservice.service.api.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnduq.microappservice.service.api.security.SimpleGrantedAuthrityMixin;
import com.johnduq.microappservice.util.JsonKeyValue;
import com.johnduq.microappservice.util.JsonUtil;
import com.johnduq.microappservice.util.TypeAuthValues;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements IJWTService {

	private static final long _1HOUR = 3600000L;

	@Override
	public String create(Authentication authentication) throws IOException {
		String username = authentication.getName();

		SecretKey secretKey = new SecretKeySpec(JsonKeyValue.THE_SECRET_VALUE_FOR_MICRO_APP_SERVICE_PROJECT.getBytes(),
				SignatureAlgorithm.HS256.getJcaName());

		Date dateCreated = new Date();
		Claims claims = getPermissionsAsClaims(authentication);

		String tokenJWT = Jwts.builder().setClaims(claims) // put permissions for token
				.setSubject(username) // put user for token
				.signWith(secretKey) // put the secret key for token
				.setIssuedAt(dateCreated) // put date create token
				.setExpiration(new Date(System.currentTimeMillis() + _1HOUR)) // put date expire token
				.compact();
		return tokenJWT;
	}

	@Override
	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(JsonKeyValue.THE_SECRET_VALUE_FOR_MICRO_APP_SERVICE_PROJECT.getBytes())
				.parseClaimsJws(resolve(token))// Validate sign <<<<<<<<------------------
				.getBody();
		return claims;
	}

	@Override
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get(TypeAuthValues.AUTHORITIES.getCode());
		Collection<? extends GrantedAuthority> authorities = getRolesAsGrnAuthority(roles);
		return authorities;
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(TypeAuthValues.BEARER_.getCode())) {
			return token.replace(TypeAuthValues.BEARER_.getCode(), "");
		} else {
			return null;
		}
	}

	private Claims getPermissionsAsClaims(Authentication authentication) throws JsonProcessingException {
		Collection<? extends GrantedAuthority> permissions = authentication.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put(TypeAuthValues.AUTHORITIES.getCode(), JsonUtil.convertObjectToJson(permissions));
		return claims;
	}

	private Collection<? extends GrantedAuthority> getRolesAsGrnAuthority(Object roles)
			throws IOException, JsonParseException, JsonMappingException {
		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthrityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

}
