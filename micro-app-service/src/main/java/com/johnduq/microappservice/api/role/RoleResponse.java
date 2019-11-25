package com.johnduq.microappservice.api.role;

import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse extends Response{

	private Role role;
	
}
