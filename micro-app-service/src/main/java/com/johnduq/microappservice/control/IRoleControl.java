package com.johnduq.microappservice.control;

import java.util.List;

import com.johnduq.microappservice.model.entity.Role;

public interface IRoleControl {

	public List<Role> findByUser(String username);
	
}
