package com.johnduq.microappservice.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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

	@Id @Column
	private Integer idPerson;
	@Column
	private String documentType;
	@Column
	private String documentNumber;
	private String firstName;
	@Column
	private String secondName;
	@Column
	private String firstSurname;
	@Column
	private String secondSurname;
	@Column @DateTimeFormat
	private Date dateBirth;
	
	
}
