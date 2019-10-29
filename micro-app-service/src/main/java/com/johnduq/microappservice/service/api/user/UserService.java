package com.johnduq.microappservice.service.api.user;

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

import com.johnduq.microappservice.control.IUserControl;
import com.johnduq.microappservice.model.dto.Response;
import com.johnduq.microappservice.model.entity.User;
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
			UserResponse userResponse = new UserResponse(iUserControl.findByIdUser(idUser));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PostMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response postUser(@RequestBody User user) {
		try {
			UserResponse userResponse = new UserResponse(iUserControl.save(user));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@PutMapping(path = UserPathValue.USER)
	@Secured({ Roles.ADMIN })
	public Response putUser(@RequestBody User user) {
		try {
			UserResponse userResponse = new UserResponse(iUserControl.save(user));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

	@DeleteMapping(path = UserPathValue.USER_ID)
	@Secured({ Roles.ADMIN })
	public Response putUser(@PathVariable(name = "id") Integer idUser) {
		try {
			UserResponse userResponse = new UserResponse(iUserControl.delete(idUser));
			return MessageUtil.addGenericSuccessMessage(userResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return MessageUtil.addGenericErrorMessage(new Response());
		}
	}

}
