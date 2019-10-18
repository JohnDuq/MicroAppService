package com.johnduq.microappservice.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IUserControl;
import com.johnduq.microappservice.model.dao.UserDAO;
import com.johnduq.microappservice.model.entity.User;

@Service
public class UserControlImpl implements IUserControl {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User findByUser(String user) {
		return userDAO.findByUser(user);
	}
	
}
