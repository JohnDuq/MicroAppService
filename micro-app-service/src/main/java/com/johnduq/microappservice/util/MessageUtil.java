package com.johnduq.microappservice.util;

import com.johnduq.microappservice.model.dto.Message;
import com.johnduq.microappservice.model.dto.Response;

public class MessageUtil {

	public static void addMessage(Response response, Message message) {
		response.getListMessages().add(message);
	}

	public static Response addGenericSuccessMessage(Response response) {
		Message message = new Message(TypeMessage.SUCCESS.getCode(), TypeMessage.SUCCESS.getName(),
				MessageFileUtil.getInstance().getMessage(MessageFileUtil.GENERAL_TITTLE_MENSAGE_EXITOSO),
				MessageFileUtil.getInstance().getMessage(MessageFileUtil.GENERAL_DETAIL_MENSAGE_EXITOSO));
		addMessage(response, message);
		return response;
	}
	
	public static Response addGenericErrorMessage(Response response) {
		Message message = new Message(TypeMessage.ERROR.getCode(), TypeMessage.ERROR.getName(),
				MessageFileUtil.getInstance().getMessage(MessageFileUtil.GENERAL_TITTLE_MENSAGE_ERROR),
				MessageFileUtil.getInstance().getMessage(MessageFileUtil.GENERAL_DETAIL_MENSAGE_ERROR));
		addMessage(response, message);
		return response;
	}

	public static Response agregarMensageGenericoCompleto(Response response, Integer typeMessageCode,
			String typeMessage, String tittle, String detail) {
		Message message = new Message(typeMessageCode, typeMessage, tittle, detail);
		addMessage(response, message);
		return response;
	}

}
