package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IUserControl;
import com.johnduq.microappservice.model.dao.UserDAO;
import com.johnduq.microappservice.model.dao.UserRoleDAO;
import com.johnduq.microappservice.model.entity.User;
import com.johnduq.microappservice.util.BCryptUtil;

@Service
public class UserControlImpl implements IUserControl {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;
	@Autowired
	private BCryptUtil bCryptUtil;

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findByUsername(String user) {
		return userDAO.findByUser(user);
	}
	
	@Override
	public User findByUserAndStatus(String username, String status) {
		return userDAO.findByUserAndStatus(username, status);
	}

	@Override
	public User findByIdUser(Integer idUser) {
		return userDAO.findByIdUser(idUser);
	}

	@Override
	public User save(User user) {
		if (user.getIdUser() == null) {
			encryptPasswordUser(user);
		} else {
			User userFind = findByIdUser(user.getIdUser());
			if (!userFind.getPassword().equals(user.getPassword())) {
				encryptPasswordUser(user);
			}
		}
		return userDAO.save(user);
	}

	@Override
	public User delete(Integer idUser) {
		userRoleDAO.deleteUserRoleByIdUser(idUser);
		User user = userDAO.findByIdUser(idUser);
		userDAO.delete(user);
		return user;
	}

	private void encryptPasswordUser(User user) {
		user.setPassword(bCryptUtil.encoder(user.getPassword()));
	}

}
