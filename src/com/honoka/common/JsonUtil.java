/**
 * JsonUtil.java
 * @Package com.tzg.common 
 * 项目　　：田字格管理平台
 * 机能　　：田娃管理
 * 説明　　：田娃情报更新
 * 備考　　：このプログラムはサンプルです。
 * 作成　　：[日付] 2015/08/14 [氏名] 樊国敬
 * 履歴：
 * [NO]  [日付]　  [Ver]　    [更新者]　  [内容]
 *　1   2015/08/14 V10L1     MBP）樊国敬　初版。
 * Copyright (C) 2016, MBP All Rights Reserved.
 */
package com.honoka.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 田娃情报更新.
 *
 * @author MBP 樊国敬
 * @see com.honoka.common
 * @version  1.0
 */
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
