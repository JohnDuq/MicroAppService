package com.johnduq.microappservice.service.api.user;

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
public class UserResponse extends Response {

	private User user;

}
