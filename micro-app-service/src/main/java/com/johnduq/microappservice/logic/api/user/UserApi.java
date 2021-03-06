package com.johnduq.microappservice.logic.api.user;

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

import com.johnduq.microappservice.logic.api.config.GeneralPathValue;
import com.johnduq.microappservice.logic.api.config.UserPathValue;
import com.johnduq.microappservice.logic.service.IRoleService;
import com.johnduq.microappservice.logic.service.IUserRoleService;
import com.johnduq.microappservice.logic.service.IUserService;
import com.johnduq.microappservice.model.Response;
import com.johnduq.microappservice.util.MessageUtil;
import com.johnduq.microappservice.util.OriginValid;
import com.johnduq.microappservice.util.Roles;
import com.johnduq.microappservice.util.TypeConsume;

@RestController
@CrossOrigin(origins = OriginValid.HTTP_LOCALHOST_4200)
@RequestMapping(value = GeneralPathValue.PRE_PATH, consumes = TypeConsume.APPLICATION_JSON, produces = TypeConsume.APPLICATION_JSON)
public class UserApi {

	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	@Autowired
	private IUserService iUserService;
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private IUserRoleService iUserRoleService;

	@GetMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response getUserService() {
		try {
			UserGetResponse userGetResponse = new UserGetResponse(iUserService.findAll());
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
			userResponse.setUser(iUserService.findByIdUser(idUser));
			userResponse.setListRolesUser(userResponse.getUser() == null ? new ArrayList()
					: iRoleService.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(userResponse.getUser() == null ? new ArrayList()
					: iRoleService.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
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
			userResponse.setUser(iUserService.findByUsername(username));
			userResponse.setListRolesUser(userResponse.getUser() == null ? new ArrayList()
					: iRoleService.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(userResponse.getUser() == null ? new ArrayList()
					: iRoleService.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
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
			userResponse.setUser(iUserService.save(userTransaction.getUser()));
			iUserRoleService.associateRolesToUser(userTransaction.getUser(), userTransaction.getListRolesUser());
			userResponse.setListRolesUser(iRoleService.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(iRoleService.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
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
			userResponse.setUser(iUserService.save(userTransaction.getUser()));
			iUserRoleService.associateRolesToUser(userTransaction.getUser(), userTransaction.getListRolesUser());
			userResponse.setListRolesUser(iRoleService.findRoleByIdUser(userResponse.getUser().getIdUser()));
			userResponse.setListRolesAvaible(iRoleService.findAvaibleRolesByIdUser(userResponse.getUser().getIdUser()));
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
			userResponse.setUser(iUserService.delete(idUser));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

}
