package com.johnduq.microappservice.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.model.entity.Permission;

public interface PermissionDAO extends CrudRepository<Permission, Integer> {

	@Query("SELECT prm FROM Permission prm WHERE prm.idPermission IN ("
			+ "	SELECT rlprm.permission.idPermission FROM RolePermission rlprm WHERE rlprm.role.idRole IN ("
			+ "		SELECT usrrl.role.idRole FROM UserRole usrrl WHERE usrrl.user.username = :username"
			+ "	)"
			+ ")")
	List<Permission> findByUser(@Param("username") String username);
	
}
