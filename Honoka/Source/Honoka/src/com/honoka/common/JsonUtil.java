
package com.honoka.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String toJson(Object obj) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();  
		return mapper.writeValueAsString(obj);
	}
	
	public static void sentJson(Object obj, HttpServletResponse response) throws JsonProcessingException, IOException{
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(JsonUtil.toJson(obj));
	}
}
