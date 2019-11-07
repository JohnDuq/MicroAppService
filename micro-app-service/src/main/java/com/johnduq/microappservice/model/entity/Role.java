package com.johnduq.microappservice.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idRole;
	@Column(unique = true)
	private String name;
	@Column
	private String description;
	
	@PrePersist
	private void preInsert() {
		this.name = this.name.toUpperCase().replace(" ", "_");
	}
	
	@PreUpdate
	private void preUpdate() {
		this.name = this.name.toUpperCase().replace(" ", "_");
	}
	
}
