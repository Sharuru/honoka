package com.honoka.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.service.APIKeyService;

@Controller
public class MainController {

	@Resource
	private APIKeyService apiKeyService;

	// 首页
	@RequestMapping(value = "/Main")
	public String mainRouter() {
		System.out.println("In Main");
		return "main";
	}

	// 仪表盘
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardRouter(ModelMap model) {
		System.out.println("In Dashboard");
		//apiKeyService.selectUsableAPIKeyByProvider("BAIDU");
		model.addAttribute("baiduAmount", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		return "dashboard/dashMain";
	}

}
