package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.RolePermission;

public interface RolePermissionDAO extends CrudRepository<RolePermission, Integer> {

}
