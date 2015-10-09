package com.honoka.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

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
		return "dashboard/dashMain";
	}

}
