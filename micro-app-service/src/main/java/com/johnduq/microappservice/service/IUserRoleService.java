package com.johnduq.microappservice.service;

import java.util.List;

import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.dao.entity.User;

public interface IUserRoleService {

	public void associateRolesToUser(User user, List<Role> listRoles);
	
}
