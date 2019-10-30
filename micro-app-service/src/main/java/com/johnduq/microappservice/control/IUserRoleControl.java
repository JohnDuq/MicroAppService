package com.johnduq.microappservice.control;

import java.util.List;

import com.johnduq.microappservice.model.entity.Role;
import com.johnduq.microappservice.model.entity.User;

public interface IUserRoleControl {

	public void associateRolesToUser(User user, List<Role> listRoles);
	
}
