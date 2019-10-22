package com.johnduq.microappservice.util;

import org.springframework.util.Base64Utils;

public class JsonKeyValue {

	private JsonKeyValue() {}
	
	public static final String THE_SECRET_VALUE_FOR_MICRO_APP_SERVICE_PROJECT = Base64Utils.encodeToString("theSecretValueForMicroAppServiceProject".getBytes());
	
}
