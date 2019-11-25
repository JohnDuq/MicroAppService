package com.johnduq.microappservice.logic.api.role;

import java.util.List;

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
public class RoleGetResponse extends Response {

	private List<Role> listRoles;
	
}
