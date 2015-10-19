package com.honoka.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.service.APIKeyService;
import com.honoka.service.CompanyService;
import com.honoka.service.DepartmentService;
import com.honoka.service.LevelService;
import com.honoka.service.MetroAdminService;
import com.honoka.service.StaffAdminService;

@Controller
public class MainController {

	@Resource
	private APIKeyService apiKeyService;
	@Resource
	private StaffAdminService staffAdminService;
	@Resource
	private CompanyService companyService;
	@Resource
	private DepartmentService departmantService;
	@Resource
	private LevelService levelService;
	@Resource
	private MetroAdminService metroAdminService;

	// 首页
	@RequestMapping(value = "/Main")
	public String mainRouter(ModelMap model) {
		System.out.println("In /Main");
		//参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		pageParaMap.put("baiduAPIKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		model.addAttribute("pageParaMap", pageParaMap);
		return "main";
	}

	// 仪表盘
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardRouter(ModelMap model) {
		System.out.println("In /dashboard");
		//参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		pageParaMap.put("inSystemStaffInfoCount", staffAdminService.countStaffInfo());
		pageParaMap.put("inSystemStationInfoCount", metroAdminService.countMetroInfo());
		pageParaMap.put("baiduUsingKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		pageParaMap.put("amapUsingKey", apiKeyService.selectUsableAPIKeyByProvider("AMAPWEB"));
		model.addAttribute("pageParaMap", pageParaMap);
		return "dashboard/dashMain";
	}

	// 员工数据管理画面初始化
	@RequestMapping(value = "/staffAdmin", method = RequestMethod.GET)
	public String staffAdminRouterInit(ModelMap model, Integer reqPage) {
		System.out.println("In Staff admin init");
		return "redirect:staffAdmin&reqPage=1";
	}

	// 地铁站点管理画面
	@RequestMapping(value = "/metroAdmin", method = RequestMethod.GET)
	public String metroAdminRouterInit(ModelMap model) {
		System.out.println("In /metroAdmin");
		return "redirect:metroAdmin&reqPage=1";
	}
	
	// LBS 应用
	@RequestMapping(value = "/lbsAppl", method = RequestMethod.GET)
	public String lbsApplRouter(ModelMap model) {
		System.out.println("In /lbsAppl");
		return "lbsAppl/lbsMain";
	}
}
