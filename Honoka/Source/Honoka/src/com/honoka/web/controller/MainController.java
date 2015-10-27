/**
 * 主要 Controller
 */
package com.honoka.web.controller;

import com.honoka.service.APIKeyService;
import com.honoka.service.MetroAdminService;
import com.honoka.service.StaffAdminService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Qualifier("APIKeyServiceImpl")
    @Resource
    private APIKeyService apiKeyService;
    @Resource
    private StaffAdminService staffAdminService;
    @Resource
    private MetroAdminService metroAdminService;

    // 首页
    @RequestMapping(value = "/Main")
    public String mainRouter(ModelMap model) {
        System.out.println("In /Main");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        pageParaMap.put("baiduAPIKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        model.addAttribute("pageParaMap", pageParaMap);
        return "main";
    }

    // 仪表盘
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardRouter(ModelMap model) {
        System.out.println("In /dashboard");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        pageParaMap.put("inSystemStaffInfoCount", staffAdminService.countStaffInfo());
        pageParaMap.put("inSystemStationInfoCount", metroAdminService.countMetroInfo());
        pageParaMap.put("baiduUsingKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        pageParaMap.put("amapUsingKey", apiKeyService.selectUsableAPIKeyByProvider("AMAPWEB"));
        model.addAttribute("pageParaMap", pageParaMap);
        return "dashboard/dashMain";
    }

    // 员工数据管理画面初始化
    @RequestMapping(value = "/staffAdmin", method = RequestMethod.GET)
    public String staffAdminRouterInit() {
        System.out.println("In Staff admin init");
        return "redirect:staffAdmin&reqPage=1";
    }

    // 地铁站点管理画面
    @RequestMapping(value = "/metroAdmin", method = RequestMethod.GET)
    public String metroAdminRouterInit() {
        System.out.println("In /metroAdmin");
        return "redirect:metroAdmin&reqPage=1";
    }

    // LBS 应用
    @RequestMapping(value = "/lbsAppl", method = RequestMethod.GET)
    public String lbsApplRouter() {
        System.out.println("In /lbsAppl");
        return "lbsAppl/lbsMain";
    }

    // 员工数据批量导入
    @RequestMapping(value = "/importStaff", method = RequestMethod.GET)
    public String importStafflRouter() {
        System.out.println("In /importStaff");
        return "staffImport/importMain";
    }
}
