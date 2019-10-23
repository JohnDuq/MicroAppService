package com.johnduq.microappservice.service.api.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnduq.microappservice.control.IRoleControl;
import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.service.api.person.PersonService;
import com.johnduq.microappservice.service.api.security.RolePathValue;
import com.johnduq.microappservice.service.config.GeneralPathValue;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.Roles;
import com.johnduq.microappservice.util.TypeConsume;

@RestController
@RequestMapping(value = GeneralPathValue.PRE_PATH, produces = TypeConsume.APPLICATION_JSON)
public class RoleService {

private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private IRoleControl iRoleControl;

	@RequestMapping(path = RolePathValue.ROLE)
	@Secured({Roles.ADMIN})
	public Response getPersonService() {
		try {
			RoleGetResponse roleGetResponse = new RoleGetResponse(iRoleControl.findAll());
			return MessageUtil.addGenericSuccessMessage(roleGetResponse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	
}
