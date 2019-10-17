package com.johnduq.microappservice.model.entity;

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
public class RolePermission {

	@Id
	private Integer idRolePermission;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idRole")
	private Role role;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idPermission")
	private Permission permission;
	
}
