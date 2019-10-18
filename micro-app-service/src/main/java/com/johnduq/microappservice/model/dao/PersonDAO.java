package com.johnduq.microappservice.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.johnduq.microappservice.model.entity.Person;

public interface PersonDAO extends CrudRepository<Person, Integer>{

}
