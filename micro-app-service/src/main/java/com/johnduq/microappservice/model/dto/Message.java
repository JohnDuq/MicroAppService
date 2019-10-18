package com.johnduq.microappservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Message {

	private Integer status;
	private String type;
	private String title;
	private String detail;

}
