package com.johnduq.microappservice.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.dao.entity.User;
import com.johnduq.microappservice.dao.repository.UserRepository;
import com.johnduq.microappservice.dao.repository.UserRoleRepository;
import com.johnduq.microappservice.logic.service.IUserService;
import com.johnduq.microappservice.util.BCryptUtil;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private BCryptUtil bCryptUtil;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String user) {
		return userRepository.findByUser(user);
	}
	
	@Override
	public User findByUserAndStatus(String username, String status) {
		return userRepository.findByUserAndStatus(username, status);
	}

	@Override
	public User findByIdUser(Integer idUser) {
		return userRepository.findByIdUser(idUser);
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
		return userRepository.save(user);
	}

	@Override
	public User delete(Integer idUser) {
		userRoleRepository.deleteUserRoleByIdUser(idUser);
		User user = userRepository.findByIdUser(idUser);
		userRepository.delete(user);
		return user;
	}

	private void encryptPasswordUser(User user) {
		user.setPassword(bCryptUtil.encoder(user.getPassword()));
	}

}
