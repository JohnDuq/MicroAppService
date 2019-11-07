package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IRoleControl;
import com.johnduq.microappservice.model.dao.RoleDAO;
import com.johnduq.microappservice.model.dao.UserRoleDAO;
import com.johnduq.microappservice.model.entity.Role;

@Service
public class RoleControlImpl implements IRoleControl {

	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Override
	public List<Role> findAll() {
		return roleDAO.findAll();
	}
	
	@Override
	public List<Role> findByUser(String username) {
		return roleDAO.findByUser(username);
	}

	@Override
	public Role findByIdRole(Integer idRole) {
		return roleDAO.findByIdRole(idRole);
	}

	@Override
	public Role save(Role role) {
		return roleDAO.save(role);
	}

	@Override
	public Role delete(Integer idRole) {
		userRoleDAO.deleteUserRoleByIdRole(idRole);
		Role role = roleDAO.findByIdRole(idRole);
		roleDAO.delete(role);
		return role;
	}

	@Override
	public List<Role> findRoleByIdUser(Integer idUser) {
		return roleDAO.findByIdUser(idUser);
	}

	@Override
	public List<Role> findAvaibleRolesByIdUser(Integer idUser) {
		return roleDAO.findAvaibleRolesByIdUser(idUser);
	}

}
