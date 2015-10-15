package com.honoka.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaceAdminController {
	// 已有兴趣点一览
	@RequestMapping(value = "/listPOI", method = RequestMethod.GET)
	public String listPOIRouter(ModelMap model) {
		System.out.println("In /listPOI");
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		// 检索库中所有兴趣点信息
		// pageParaMap.put("reqAddr", "");
		// pageParaMap.put("bdReqStatus", "info");
		// pageParaMap.put("apReqStatus", "info");
		// pageParaMap.put("bdGeocodingResult", "百度通道等待用户输入……");
		// pageParaMap.put("apGeocodingResult", "高德通道等待用户输入……");
		model.addAttribute("pageParaMap", pageParaMap);
		return "placeSearch/listPOI";
	}

}
