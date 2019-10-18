package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.Permission;

public interface PermissionDAO extends CrudRepository<Permission, Integer> {

}
