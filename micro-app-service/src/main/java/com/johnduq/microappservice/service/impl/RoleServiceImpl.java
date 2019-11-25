package com.johnduq.microappservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.dao.repository.RoleRepository;
import com.johnduq.microappservice.dao.repository.UserRoleRepository;
import com.johnduq.microappservice.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public List<Role> findByUser(String username) {
		return roleRepository.findByUser(username);
	}

	@Override
	public Role findByIdRole(Integer idRole) {
		return roleRepository.findByIdRole(idRole);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role delete(Integer idRole) {
		userRoleRepository.deleteUserRoleByIdRole(idRole);
		Role role = roleRepository.findByIdRole(idRole);
		roleRepository.delete(role);
		return role;
	}

	@Override
	public List<Role> findRoleByIdUser(Integer idUser) {
		return roleRepository.findByIdUser(idUser);
	}

	@Override
	public List<Role> findAvaibleRolesByIdUser(Integer idUser) {
		return roleRepository.findAvaibleRolesByIdUser(idUser);
	}

}
