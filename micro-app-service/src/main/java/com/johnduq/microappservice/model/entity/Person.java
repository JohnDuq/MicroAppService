package com.johnduq.microappservice.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	private Integer idPerson;
	private String documentNumber;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private Date dateBirth;
	
	
}
