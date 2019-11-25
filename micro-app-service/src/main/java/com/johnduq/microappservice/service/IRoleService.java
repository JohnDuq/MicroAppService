package com.johnduq.microappservice.service;

import java.util.List;

import com.johnduq.microappservice.dao.entity.Role;

public interface IRoleService {

	public List<Role> findAll();
	public List<Role> findRoleByIdUser(Integer idUser);
	public Role findByIdRole(Integer idRole);
	public List<Role> findByUser(String username);
	public List<Role> findAvaibleRolesByIdUser(Integer idUser);
	public Role save(Role role);
	public Role delete(Integer idRole);
	
	
}
