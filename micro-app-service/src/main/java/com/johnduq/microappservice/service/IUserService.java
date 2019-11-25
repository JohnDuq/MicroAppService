package com.johnduq.microappservice.service;

import java.util.List;

import com.johnduq.microappservice.dao.entity.User;

public interface IUserService {

	public List<User> findAll();
	public User findByIdUser(Integer idUser);
	public User findByUsername(String username);
	public User findByUserAndStatus(String username, String status);
	public User save(User user);
	public User delete(Integer idUser);
	
}
