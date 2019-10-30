package com.johnduq.microappservice.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.model.entity.UserRole;

public interface UserRoleDAO extends CrudRepository<UserRole, Integer>{

	@Modifying
	@Query("DELETE FROM UserRole usrrl WHERE usrrl.user.idUser = :idUser")
	void deleteUserRoleOld(@Param("idUser")Integer idUser);
	
}
