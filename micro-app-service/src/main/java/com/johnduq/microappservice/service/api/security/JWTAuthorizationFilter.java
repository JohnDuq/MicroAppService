package com.johnduq.microappservice.service.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.johnduq.microappservice.service.api.auth.IJWTService;
import com.johnduq.microappservice.util.TypeAuthValues;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private IJWTService ijwtService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, IJWTService ijwtService) {
		super(authenticationManager);
		this.ijwtService = ijwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(TypeAuthValues.AUTHORIZATION.getCode());
		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = null;
		if (ijwtService.validate(header)) {
			authenticationToken = new UsernamePasswordAuthenticationToken(ijwtService.getUsername(header), null, ijwtService.getRoles(header));
		}
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	protected boolean requiresAuthentication(String header) {
		if (header == null || !header.toLowerCase().startsWith(TypeAuthValues.BEARER_.getCode())) {
			return true;
		}
		return false;
	}

}
