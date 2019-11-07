package com.johnduq.microappservice.service.api.user;

import java.util.List;

import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.Role;
import com.johnduq.microappservice.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction extends Response {

	private User user;
	private List<Role> listRolesUser;
	private List<Role> listRolesAvaible;

}
