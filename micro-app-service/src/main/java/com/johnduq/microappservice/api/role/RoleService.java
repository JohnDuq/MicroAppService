package com.johnduq.microappservice.api.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnduq.microappservice.api.config.GeneralPathValue;
import com.johnduq.microappservice.api.config.RolePathValue;
import com.johnduq.microappservice.dao.entity.Role;
import com.johnduq.microappservice.model.Response;
import com.johnduq.microappservice.service.IRoleService;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.OriginValid;
import com.johnduq.microappservice.util.Roles;
import com.johnduq.microappservice.util.TypeConsume;


@RestController
@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
@RequestMapping(value = GeneralPathValue.PRE_PATH, consumes = TypeConsume.APPLICATION_JSON, produces = TypeConsume.APPLICATION_JSON)
public class RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private IRoleService iRoleControl;

	@GetMapping(path = RolePathValue.ROLE)
	@Secured({ Roles.ADMIN })
	public Response getRoleService() {
		try {
			RoleGetResponse roleGetResponse = new RoleGetResponse(iRoleControl.findAll());
			return MessageUtil.addGenericSuccessMessage(roleGetResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@GetMapping(path = RolePathValue.ROLE_ID)
	@Secured({ Roles.ADMIN })
	public Response findRoleById(@PathVariable(name = "id") Integer idRole) {
		try {
			RoleResponse roleResponse = new RoleResponse(iRoleControl.findByIdRole(idRole));
			return MessageUtil.addGenericSuccessMessage(roleResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PostMapping(path = RolePathValue.ROLE)
	@Secured({ Roles.ADMIN })
	public Response postRole(@RequestBody Role role) {
		try {
			RoleResponse roleResponse = new RoleResponse(iRoleControl.save(role));
			return MessageUtil.addGenericSuccessMessage(roleResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PutMapping(path = RolePathValue.ROLE)
	@Secured({ Roles.ADMIN })
	public Response putRole(@RequestBody Role role) {
		try {
			RoleResponse roleResponse = new RoleResponse(iRoleControl.save(role));
			return MessageUtil.addGenericSuccessMessage(roleResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@DeleteMapping(path = RolePathValue.ROLE_ID)
	@Secured({ Roles.ADMIN })
	public Response deleteRole(@PathVariable(name = "id") Integer idRole) {
		try {
			RoleResponse roleResponse = new RoleResponse(iRoleControl.delete(idRole));
			return MessageUtil.addGenericSuccessMessage(roleResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

}
