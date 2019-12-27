package com.johnduq.microappservice.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.dao.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	@Query("SELECT usr FROM User usr ORDER BY usr.idUser")
	List<User> findAll();
	
	@Query("SELECT usr FROM User usr WHERE usr.username = :username")
	User findByUser(@Param("username") String username);
	
	@Query("SELECT usr FROM User usr WHERE usr.username = :username AND usr.status = :status")
	User findByUserAndStatus(@Param("username") String username, @Param("status") String status);
	
}
