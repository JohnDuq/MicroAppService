package com.johnduq.microappservice.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.model.entity.User;

public interface UserDAO extends CrudRepository<User, Integer>{

	@Query("SELECT usr FROM User usr ORDER BY usr.idUser")
	List<User> findAll();
	
	@Query("SELECT usr FROM User usr WHERE usr.idUser = :idUser")
	User findByIdUser(@Param("idUser") Integer idUser);
	
	@Query("SELECT usr FROM User usr WHERE usr.username = :username")
	User findByUser(@Param("username") String username);
	
}
