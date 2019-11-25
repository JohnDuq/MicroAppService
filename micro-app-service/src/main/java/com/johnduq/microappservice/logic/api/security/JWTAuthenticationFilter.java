package com.johnduq.microappservice.logic.api.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import com.johnduq.microappservice.dao.entity.User;
import com.johnduq.microappservice.logic.api.auth.IJWTApi;
import com.johnduq.microappservice.logic.api.config.GeneralPathValue;
import com.johnduq.microappservice.model.Response;
import com.johnduq.microappservice.util.JsonUtil;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.TypeAuthValues;
import com.johnduq.microappservice.util.TypeContentWeb;
import com.johnduq.microappservice.util.TypeMessage;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String MICRO_APP_SERVICE = "MicroAppService";
	private AuthenticationManager authenticationManager;
	private IJWTApi ijwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, IJWTApi ijwtService) {
		this.authenticationManager = authenticationManager;
		this.ijwtService = ijwtService;
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
			Authentication authentication) throws IOException, ServletException {

		String tokenJWT = ijwtService.create(authentication);

		response.addHeader(TypeAuthValues.AUTHORIZATION.getCode(), TypeAuthValues.BEARER + " " + tokenJWT);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put(TypeAuthValues.TOKEN.getCode(), tokenJWT);
		body.put(TypeAuthValues.USER.getCode(), authentication.getName());
		body.put(TypeMessage.ERROR.getName(), null);

		response.getWriter().write(JsonUtil.convertObjectToJson(body));
		response.setStatus(200);
		response.setContentType(TypeContentWeb.APPLICATION_JSON.getCode());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put(MICRO_APP_SERVICE, MessageUtil.addGenericErrorMessage(new Response()));
		
		body.put(TypeAuthValues.TOKEN.getCode(), null);
		body.put(TypeAuthValues.USER.getCode(), null);
		body.put(TypeMessage.ERROR.getName(), failed.getMessage());
		
		
		response.getWriter().write(JsonUtil.convertObjectToJson(body));
		response.setStatus(200);
		response.setContentType(TypeContentWeb.APPLICATION_JSON.getCode());
	}

}
