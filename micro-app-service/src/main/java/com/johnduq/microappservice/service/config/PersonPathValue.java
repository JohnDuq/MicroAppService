package com.johnduq.microappservice.service.config;

public class PersonPathValue {

	private PersonPathValue(){}
	
	public static final String PERSON = "/person";
	public static final String PERSON_FIND_BY_IDPERSON = "/identity/{idPerson}";
	public static final String PERSON_FIND_BY_DOCNUMBER = "/identity/findByDocumentNumber/{documentNumber}";
	public static final String PERSON_FIND_BY_DOCTYPE_DOCNUMBER = "/identity/findByDocTypeDocNumber/{documentType}/{documentNumber}";
	
}
