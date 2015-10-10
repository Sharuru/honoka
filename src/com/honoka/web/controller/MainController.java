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
		//TODO：这里的 Service 未来需要组合，返回一个批量的 API KEY / 额度结果集
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("baiduKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		obj.put("baiduAmount",100000 - apiKeyService.selectAmountByAPIKey(obj.get("baiduKey").toString()));
		obj.put("amapKey", apiKeyService.selectUsableAPIKeyByProvider("AMAPWEB"));
		//obj.put("baiduAmount",100000 - apiKeyService.selectAmountByAPIKey(obj.get("baiduKey").toString()));
		model.addAttribute("obj", obj);
		return "dashboard/dashMain";
	}
	
	//LBS 计算
	@RequestMapping(value = "/lbsCalc", method = RequestMethod.GET)
	public String lbsCalcRouter(ModelMap model){
		System.out.println("In LBS calc");
		return "lbsCalc/lbsMain";
	}

}
