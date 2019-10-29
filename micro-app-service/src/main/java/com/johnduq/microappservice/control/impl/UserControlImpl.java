package com.johnduq.microappservice.control.impl;

import java.util.List;

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
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	@Override
	public User findByUsername(String user) {
		return userDAO.findByUser(user);
	}

	@Override
	public User findByIdUser(Integer idUser) {
		return userDAO.findByIdUser(idUser);
	}

	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	public User delete(Integer idUser) {
		User user = userDAO.findByIdUser(idUser);
		userDAO.delete(user);
		return user;
	}
	
}
