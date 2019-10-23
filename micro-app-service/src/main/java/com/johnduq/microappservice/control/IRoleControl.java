package com.johnduq.microappservice.control;

import java.util.List;

import com.johnduq.microappservice.model.entity.Role;

public interface IRoleControl {

	public List<Role> findAll();
	public Role findByIdRole(Integer idRole);
	public List<Role> findByUser(String username);
	public Role save(Role role);
	public Role delete(Integer idRole);
	
	
}
