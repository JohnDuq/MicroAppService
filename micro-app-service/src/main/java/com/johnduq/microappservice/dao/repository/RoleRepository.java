package com.johnduq.microappservice.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.johnduq.microappservice.dao.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	@Query("SELECT rl FROM Role rl")
	List<Role> findAll();

	@Query("SELECT rl FROM Role rl WHERE rl.idRole = :idRole")
	Role findByIdRole(@Param("idRole") Integer idRole);

	@Query("SELECT usrrl.role FROM UserRole usrrl WHERE usrrl.user.username = :username")
	List<Role> findByUser(@Param("username") String username);

	@Query("SELECT usrrl.role FROM UserRole usrrl WHERE usrrl.user.idUser = :idUser")
	List<Role> findByIdUser(@Param("idUser") Integer idUser);

	@Query("SELECT rl FROM Role rl "
			+ "WHERE rl.idRole NOT IN ("
			+ "	SELECT usrrl.role.idRole FROM UserRole usrrl WHERE usrrl.user.idUser = :idUser)")
	List<Role> findAvaibleRolesByIdUser(@Param("idUser") Integer idUser);

}
