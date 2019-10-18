package com.johnduq.microappservice.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response {

	private List<Message> listMessages = new ArrayList<Message>();
	
}
