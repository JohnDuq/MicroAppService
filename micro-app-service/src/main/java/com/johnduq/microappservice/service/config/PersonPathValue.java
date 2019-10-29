package com.johnduq.microappservice.service.config;

public class PersonPathValue {

	private PersonPathValue() {
	}

	public static final String PERSON = "/person";
	public static final String PERSON_FIND_BY_ID = PERSON + "/{idPerson}";
	public static final String PERSON_FIND_BY_DOCNUMBER = PERSON + "/findByDocumentNumber/{documentNumber}";
	public static final String PERSON_FIND_BY_DOCTYPE_DOCNUMBER = PERSON + "/findByDocTypeDocNumber/{documentType}/{documentNumber}";

}
