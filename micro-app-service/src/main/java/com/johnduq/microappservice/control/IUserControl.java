package com.johnduq.microappservice.control;

import java.util.List;

import com.johnduq.microappservice.model.entity.User;

public interface IUserControl {

	public List<User> findAll();
	public User findByIdUser(Integer idUser);
	public User findByUsername(String username);
	public User findByUserAndStatus(String username, String status);
	public User save(User user);
	public User delete(Integer idUser);
	
}
