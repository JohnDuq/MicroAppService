package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.Role;

public interface RoleDAO extends CrudRepository<Role, Integer>{

}
