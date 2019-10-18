package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.UserRole;

public interface UserRoleDAO extends CrudRepository<UserRole, Integer>{

}
