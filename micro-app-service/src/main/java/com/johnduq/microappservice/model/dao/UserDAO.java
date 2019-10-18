package com.johnduq.microappservice.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.model.entity.User;

public interface UserDAO extends CrudRepository<User, Integer>{

	@Query("SELECT usr FROM User usr WHERE usr.userName = :userName")
	User findByUser(@Param("userName") String userName);
	
}
