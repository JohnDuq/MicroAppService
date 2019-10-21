package com.johnduq.microappservice.service.api.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.User;
import com.johnduq.microappservice.service.config.GeneralPathValue;
import com.johnduq.microappservice.util.JsonUtil;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.TypeAuthValues;
import com.johnduq.microappservice.util.TypeContentWeb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

	private static final String THE_SECRET_VALUE_FOR_MICRO_APP_SERVICE_PROJECT = "theSecretValueForMicroAppServiceProject";
	private AuthenticationManager authenticationManager;

	public JWTAuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher(GeneralPathValue.PATH_LOGIN, RequestMethod.POST.toString()));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		//get values from form-data
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if(username == null && password == null) {
			//get values from body
			User user;
			try {
				user = (User) JsonUtil.convertJsonToObject(request.getInputStream(), User.class);
				username = user.getUsername();
				password = user.getPassword();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		username = username.trim();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return this.authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = authResult.getName();
		
		SecretKey secretKey = new SecretKeySpec(THE_SECRET_VALUE_FOR_MICRO_APP_SERVICE_PROJECT.getBytes(), SignatureAlgorithm.HS256.getJcaName());
		
		Date dateCreated = new Date();
		Claims claims = getPermissionsAsClaims(authResult);

		String tokenJWT = Jwts.builder()
				.setClaims(claims)													//put permissions for token
				.setSubject(username)												//put user for token
				.signWith(secretKey)												//put the secret key for token
				.setIssuedAt(dateCreated)											//put date create token
				.setExpiration(new Date(System.currentTimeMillis() + 3600000L))		//put date expire token
				.compact();

		response.addHeader(TypeAuthValues.AUTHORIZATION.getCode(), TypeAuthValues.BEARER + " " + tokenJWT);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", tokenJWT);
		body.put("user", username);

		response.getWriter().write(JsonUtil.convertObjectToJson(body));
		response.setStatus(200);
		response.setContentType(TypeContentWeb.APPLICATION_JSON.getCode());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("MicroAppService", MessageUtil.addGenericErrorMessage(new Response()));
		body.put("error", failed.getMessage());
		response.getWriter().write(JsonUtil.convertObjectToJson(body));
		response.setStatus(401);
		response.setContentType(TypeContentWeb.APPLICATION_JSON.getCode());
	}

	private Claims getPermissionsAsClaims(Authentication authResult) throws JsonProcessingException {
		Collection<? extends GrantedAuthority> permissions = authResult.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put(TypeAuthValues.AUTHORITIES.getCode(), JsonUtil.convertObjectToJson(permissions));
		return claims;
	}

}
