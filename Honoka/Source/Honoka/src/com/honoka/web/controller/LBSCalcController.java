package com.honoka.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.service.APIKeyService;

@Controller
public class LBSCalcController {

	@Resource
	private APIKeyService apiKeyService;
	
	//地址解析
	@RequestMapping(value = "/geoCoding", method = RequestMethod.GET)
	public String geoCodingRouter(ModelMap model){
		System.out.println("In Geo Coding");
		return "lbsCalc/geoCoding";
	}

}
