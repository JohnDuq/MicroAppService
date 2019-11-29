package com.johnduq.microappservice.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.dao.entity.User;
import com.johnduq.microappservice.dao.entity.UserRole;
import com.johnduq.microappservice.dao.repository.RoleRepository;
import com.johnduq.microappservice.dao.repository.UserRoleRepository;
import com.johnduq.microappservice.logic.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Transactional
	@Override
	public void associateRolesToUser(User user, List<Role> listRoles) {
		userRoleRepository.deleteUserRoleByIdUser(user.getIdUser());
		UserRole userRole;
		for (Role role : listRoles) {
			Role roleFind = roleRepository.findByIdRole(role.getIdRole());
			if (roleFind != null) {
				userRole = new UserRole(null, role, user);
				userRoleRepository.save(userRole);
			}
		}
	}

}
