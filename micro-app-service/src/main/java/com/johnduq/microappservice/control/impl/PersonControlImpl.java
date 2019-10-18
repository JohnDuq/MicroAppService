package com.johnduq.microappservice.control.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnduq.microappservice.control.IPersonControl;
import com.johnduq.microappservice.model.dao.PersonDAO;
import com.johnduq.microappservice.model.entity.Person;

@Service
public class PersonControlImpl implements IPersonControl {

	@Autowired
	private PersonDAO personDAO;

	@Override
	public List<Person> getPersonService() {
		return (List<Person>) personDAO.findAll();
	}

}
