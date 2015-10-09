package com.honoka.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;

@Controller
public class LBSCalcController {

	@Resource
	private APIKeyService apiKeyService;
	@Resource
	private BaiduAPIService baiduAPIService;
	
	//地址解析初始画面
	@RequestMapping(value = "/geoCoding", method = RequestMethod.GET)
	public String geoCodingRouter(ModelMap model){
		System.out.println("In Geo Coding");
		return "lbsCalc/geoCoding";
	}
	
	//请求地址解析
	@RequestMapping(value = "/reqGeoCoding", method = RequestMethod.POST)
	public String reqGeoCodingRouter(ModelMap model, String reqAddr){
		System.out.println("In REQ Geo Coding");
		System.out.println("Get: reqAddr is: " + reqAddr);
		baiduAPIService.BaiduGeoCoding(reqAddr);
		return "lbsCalc/geoCoding";
	}

}
