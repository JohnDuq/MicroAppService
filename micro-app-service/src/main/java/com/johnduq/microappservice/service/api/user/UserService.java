package com.johnduq.microappservice.service.api.user;

import java.util.ArrayList;

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

import com.johnduq.microappservice.control.IRoleControl;
import com.johnduq.microappservice.control.IUserControl;
import com.johnduq.microappservice.control.IUserRoleControl;
import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.service.config.GeneralPathValue;
import com.johnduq.microappservice.service.config.UserPathValue;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.OriginValid;
import com.johnduq.microappservice.util.Roles;
import com.johnduq.microappservice.util.TypeConsume;

@RestController
@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
@RequestMapping(value = GeneralPathValue.PRE_PATH, consumes = TypeConsume.APPLICATION_JSON, produces = TypeConsume.APPLICATION_JSON)
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserControl iUserControl;
	@Autowired
	private IRoleControl iRoleControl;
	@Autowired
	private IUserRoleControl iUserRoleControl;

	@GetMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response getUserService() {
		try {
			UserGetResponse userGetResponse = new UserGetResponse(iUserControl.findAll());
			return MessageUtil.addGenericSuccessMessage(userGetResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@GetMapping(path = UserPathValue.USER_ID)
	@Secured({ Roles.ADMIN })
	public Response findByIdUser(@PathVariable(name = "id") Integer idUser) {
		try {
			UserTransaction userResponse = new UserTransaction();
			userResponse.setUser(iUserControl.findByIdUser(idUser));
			userResponse.setListRolesUser(userResponse.getUser() == null ? new ArrayList()
					: iRoleControl.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(userResponse.getUser() == null ? new ArrayList()
					: iRoleControl.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@GetMapping(path = UserPathValue.USER_USERNAME)
	@Secured({ Roles.ADMIN })
	public Response findByIdUser(@PathVariable(name = "username") String username) {
		try {
			UserTransaction userResponse = new UserTransaction();
			userResponse.setUser(iUserControl.findByUsername(username));
			userResponse.setListRolesUser(userResponse.getUser() == null ? new ArrayList()
					: iRoleControl.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(userResponse.getUser() == null ? new ArrayList()
					: iRoleControl.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PostMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response postUser(@RequestBody UserTransaction userTransaction) {
		try {
			UserTransaction userResponse = new UserTransaction();
			userResponse.setUser(iUserControl.save(userTransaction.getUser()));
			iUserRoleControl.associateRolesToUser(userTransaction.getUser(), userTransaction.getListRolesUser());
			userResponse.setListRolesUser(iRoleControl.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(iRoleControl.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PutMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response putUser(@RequestBody UserTransaction userTransaction) {
		try {
			UserTransaction userResponse = new UserTransaction();
			userResponse.setUser(iUserControl.save(userTransaction.getUser()));
			iUserRoleControl.associateRolesToUser(userTransaction.getUser(), userTransaction.getListRolesUser());
			userResponse.setListRolesUser(iRoleControl.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(iRoleControl.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@DeleteMapping(path = UserPathValue.USER_ID)
	@Secured({ Roles.ADMIN })
	public Response deleteUser(@PathVariable(name = "id") Integer idUser) {
		try {
			UserTransaction userResponse = new UserTransaction();
			userResponse.setUser(iUserControl.delete(idUser));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

}
