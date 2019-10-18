package com.johnduq.microappservice.service.api.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnduq.microappservice.control.IPersonControl;
import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.service.config.PersonPathValue;
import com.johnduq.microappservice.service.config.PrePathValue;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.TypeConsume;

@RestController
@RequestMapping(value = PrePathValue.PRE_PATH, produces = TypeConsume.APPLICATION_JSON)
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private IPersonControl iPersonControl;

	@GetMapping(path = PersonPathValue.PERSON)
	public Response getPersonService() {
		try {
			PersonGetResponse personResponse = new PersonGetResponse(iPersonControl.getPersonService());
			return MessageUtil.addGenericSuccessMessage(personResponse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

}
