package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IUserRoleControl;
import com.johnduq.microappservice.model.dao.UserRoleDAO;
import com.johnduq.microappservice.model.entity.Role;
import com.johnduq.microappservice.model.entity.User;
import com.johnduq.microappservice.model.entity.UserRole;

@Service
public class UserRoleControlImpl implements IUserRoleControl {

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public void associateRolesToUser(User user, List<Role> listRoles) {
		userRoleDAO.deleteUserRoleOld(user.getIdUser());
		UserRole userRole;
		for (Role role : listRoles) {
			userRole = new UserRole(null, user, role);
			userRoleDAO.save(userRole);
		}
	}

}
