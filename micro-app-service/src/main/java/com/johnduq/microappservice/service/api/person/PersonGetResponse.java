package com.johnduq.microappservice.service.api.person;

import java.util.List;

import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonGetResponse extends Response {

	private List<Person> listPersons;

}
