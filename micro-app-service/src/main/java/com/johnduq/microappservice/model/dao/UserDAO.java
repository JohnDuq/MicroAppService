package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.User;

public interface UserDAO extends CrudRepository<User, Integer>{

}
