package com.johnduq.microappservice.service.api.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnduq.microappservice.control.IPermissionControl;
import com.johnduq.microappservice.control.IUserControl;
import com.johnduq.microappservice.model.entity.Permission;
import com.johnduq.microappservice.model.entity.User;

@Service
public class UserSecurity implements UserDetailsService {

	private static final String USUARIO_NO_TIENE_PERMISOS = "Usuario no tiene permisos";

	private static final String USUARIO_NO_EXISTE = "Usuario no existe:";

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurity.class);

	@Autowired
	private IUserControl iUserControl;
	@Autowired
	private IPermissionControl iPermissionControl;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = iUserControl.findByUser(userName);
		if (user == null) {
			LOGGER.error(USUARIO_NO_EXISTE + userName);
			throw new UsernameNotFoundException(USUARIO_NO_EXISTE + userName);
		}
		List<Permission> lPermission = iPermissionControl.findByUser(userName);
		
		if (lPermission.isEmpty()) {
			LOGGER.error(USUARIO_NO_TIENE_PERMISOS);
			throw new UsernameNotFoundException(USUARIO_NO_TIENE_PERMISOS);
		}
		
		List<GrantedAuthority> lAuthorities = new ArrayList<GrantedAuthority>();

		lPermission.forEach(permission -> lAuthorities.add(new SimpleGrantedAuthority(permission.getName())));

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getPassword(), "ENABLE".equals(user.getStatus()), true, true, true,
				lAuthorities);

		return userDetails;
	}

}
