package com.johnduq.microappservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.johnduq.microappservice.service.api.security.JWTAuthFilter;
import com.johnduq.microappservice.service.api.security.UserSecurity;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurity userSecurity;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super.configure(http);
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/locale").permitAll().anyRequest()
				.authenticated().and()
				.addFilter(new JWTAuthFilter(authenticationManager()))
				.csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userSecurity).passwordEncoder(bCryptPasswordEncoder);
	}
}
