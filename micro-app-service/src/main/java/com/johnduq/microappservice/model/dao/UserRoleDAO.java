package com.johnduq.microappservice.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.johnduq.microappservice.model.entity.UserRole;

public interface UserRoleDAO extends CrudRepository<UserRole, Integer> {

	@Transactional
	@Modifying
	@Query("DELETE FROM UserRole usrrl WHERE usrrl.role.idRole = :idRole")
	void deleteUserRoleByIdRole(@Param("idRole") Integer idRole);

	@Transactional
	@Modifying
	@Query("DELETE FROM UserRole usrrl WHERE usrrl.user.idUser = :idUser")
	void deleteUserRoleByIdUser(@Param("idUser") Integer idUser);

}
