package com.johnduq.microappservice.service.config;

public class PersonPathValue {

	private PersonPathValue(){}
	
	public static final String PERSON = "/person";
	public static final String PERSON_FIND_BY_ID = "/person/{idPerson}";
	public static final String PERSON_FIND_BY_DOCNUMBER = "/person/findByDocumentNumber/{documentNumber}";
	public static final String PERSON_FIND_BY_DOCTYPE_DOCNUMBER = "/person/findByDocTypeDocNumber/{documentType}/{documentNumber}";
	
}
