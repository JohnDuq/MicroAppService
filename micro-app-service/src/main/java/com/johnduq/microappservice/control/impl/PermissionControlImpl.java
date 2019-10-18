package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IPermissionControl;
import com.johnduq.microappservice.model.dao.PermissionDAO;
import com.johnduq.microappservice.model.entity.Permission;

@Service
public class PermissionControlImpl implements IPermissionControl {

	@Autowired
	private PermissionDAO permissionDAO;
	
	@Override
	public List<Permission> findByUser(String userName) {
		return permissionDAO.findByUser(userName);
	}

}
