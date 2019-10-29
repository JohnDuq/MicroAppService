package com.johnduq.microappservice.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Response implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Message> listMessages = new ArrayList<Message>();
	
}
