package com.johnduq.microappservice.api.user;

import java.util.List;

import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.dao.entity.User;
import com.johnduq.microappservice.model.Response;

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
