package com.johnduq.microappservice.logic.api.user;

import java.util.List;

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
public class UserGetResponse extends Response {

	private List<User> listUsers;
	
}
