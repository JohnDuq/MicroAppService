package com.johnduq.microappservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageFileUtil {

	private static final String MESSAGES_PROPERTIES ="/messages.properties";
	private static MessageFileUtil messageFileUtil;
	public static Properties messagesProperties;
	
	public static final String GENERAL_TITTLE_MENSAGE_EXITOSO = "GENERAL_TITTLE_MENSAGE_EXITOSO";
	public static final String GENERAL_DETAIL_MENSAGE_EXITOSO = "GENERAL_DETAIL_MENSAGE_EXITOSO";
	public static final String GENERAL_TITTLE_MENSAGE_ERROR = "GENERAL_TITTLE_MENSAGE_ERROR";
	public static final String GENERAL_DETAIL_MENSAGE_ERROR = "GENERAL_DETAIL_MENSAGE_ERROR";
	
	private MessageFileUtil(){
		try (InputStream input = MessageFileUtil.class.getResourceAsStream(MESSAGES_PROPERTIES)) {
			messagesProperties = new Properties();
            messagesProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public static MessageFileUtil getInstance(){
		if(null == messageFileUtil){
			messageFileUtil = new MessageFileUtil();
		}
		return messageFileUtil;
	}
	
	public static String getMessage(String key){
		return messagesProperties.getProperty(key);
	}
	
}
