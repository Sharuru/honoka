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
		// TODO：这里的 Service 未来需要组合，返回一个批量的 API KEY / 额度结果集
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("baiduKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		obj.put("baiduAmount", 100000 - apiKeyService.selectAmountByAPIKey(obj.get("baiduKey").toString()));
		obj.put("amapKey", apiKeyService.selectUsableAPIKeyByProvider("AMAPWEB"));
		// obj.put("baiduAmount",100000 -
		// apiKeyService.selectAmountByAPIKey(obj.get("baiduKey").toString()));
		model.addAttribute("obj", obj);
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
		System.out.println("In Metro admin init");
		return "redirect:metroAdmin&reqPage=1";
	}

	// LBS 计算
	@RequestMapping(value = "/lbsCalc", method = RequestMethod.GET)
	public String lbsCalcRouter(ModelMap model) {
		System.out.println("In LBS calc");
		return "lbsCalc/lbsMain";
	}

}
