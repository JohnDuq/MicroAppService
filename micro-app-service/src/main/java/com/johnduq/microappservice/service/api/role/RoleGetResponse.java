package com.johnduq.microappservice.service.api.role;

import java.util.List;

import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleGetResponse extends Response {

	private List<Role> listPersons;
	
}
