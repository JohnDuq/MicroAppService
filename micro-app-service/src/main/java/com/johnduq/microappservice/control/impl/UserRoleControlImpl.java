package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnduq.microappservice.control.IUserRoleControl;
import com.johnduq.microappservice.model.dao.RoleDAO;
import com.johnduq.microappservice.model.dao.UserRoleDAO;
import com.johnduq.microappservice.model.entity.Role;
import com.johnduq.microappservice.model.entity.User;
import com.johnduq.microappservice.model.entity.UserRole;

@Service
public class UserRoleControlImpl implements IUserRoleControl {

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Transactional
	@Override
	public void associateRolesToUser(User user, List<Role> listRoles) {
		userRoleDAO.deleteUserRoleByIdUser(user.getIdUser());
		UserRole userRole;
		for (Role role : listRoles) {
			Role roleFind = roleDAO.findByIdRole(role.getIdRole());
			if (roleFind != null) {
				userRole = new UserRole(null, user, role);
				userRoleDAO.save(userRole);
			}
		}
	}

}
