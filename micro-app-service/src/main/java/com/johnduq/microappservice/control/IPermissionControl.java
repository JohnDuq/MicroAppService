package com.johnduq.microappservice.control;

import java.util.List;

import com.johnduq.microappservice.model.entity.Permission;

public interface IPermissionControl {

	List<Permission> findByUser(String userName);
	
}
