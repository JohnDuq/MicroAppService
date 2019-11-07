package com.johnduq.microappservice.service.api.user;

import java.util.List;

import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.User;

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
