package com.johnduq.microappservice.control;

import com.johnduq.microappservice.model.entity.User;

public interface IUserControl {

	User findByUser(String user);
	
}
