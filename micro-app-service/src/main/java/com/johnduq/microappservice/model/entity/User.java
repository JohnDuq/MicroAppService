package com.johnduq.microappservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class User {

	@Id @Column
	private Integer idUser;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idPerson")
	private Person person;
	@Column
	private String user;
	@Column
	private String password;
	@Column
	private String status;
	
	
}
