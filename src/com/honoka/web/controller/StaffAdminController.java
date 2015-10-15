package com.honoka.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.entity.Staff;
import com.honoka.service.CompanyService;
import com.honoka.service.DepartmentService;
import com.honoka.service.LevelService;
import com.honoka.service.StaffAdminService;

@Controller
public class StaffAdminController {

	@Resource
	private StaffAdminService staffAdminService;
	@Resource
	private CompanyService companyService;
	@Resource
	private DepartmentService departmantService;
	@Resource
	private LevelService levelService;

	// 员工数据管理画面
	@RequestMapping(value = "/staffAdmin&reqPage={reqPage}", method = RequestMethod.GET)
	public String staffAdminRouter(ModelMap model, @PathVariable Integer reqPage) {
		System.out.println("In Staff admin");
		System.out.println("currPage = " + reqPage);
		model.addAttribute("currPage", reqPage);
		model.addAttribute("totalCount", staffAdminService.countStaffInfo());
		List<Staff> staffList = staffAdminService.selectStaffInfoByPage(reqPage);
		// 翻译
		Map<String, String> comMap = companyService.getCompanyMap();
		Map<String, String> deptMap = departmantService.getDeptMap();
		Map<String, String> levelMap = levelService.getLevelMap();
		for (int i = 0; i < staffList.size(); i++) {
			staffList.get(i).setStaffComId(comMap.get(staffList.get(i).getStaffComId()));
			staffList.get(i).setStaffDeptId(deptMap.get(staffList.get(i).getStaffDeptId()));
			staffList.get(i).setStaffLevelId(levelMap.get(staffList.get(i).getStaffLevelId()));
		}
		model.addAttribute("staffInfoList", staffList);
		return "staffAdmin/staffMain";
	}
	
	// 员工数据详情
	@RequestMapping(value = "/staffDetail&reqStaffId={reqId}", method = RequestMethod.GET)
	public String staffDetailRouter(ModelMap model, @PathVariable String reqId) {
		System.out.println("In /staffDetail&reqStaffId=" + reqId);
		//参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		model.addAttribute("totalCount", staffAdminService.countStaffInfo());
		Staff staffDetail = staffAdminService.selectStaffDetailByStaffId(reqId);
		// 翻译
		Map<String, String> comMap = companyService.getCompanyMap();
		Map<String, String> deptMap = departmantService.getDeptMap();
		Map<String, String> levelMap = levelService.getLevelMap();
//		staffDetail.setStaffComId(comMap.get(staffDetail.getStaffComId()));
//		staffDetail.setStaffDeptId(deptMap.get(staffDetail.getStaffDeptId()));
//		staffDetail.setStaffLevelId(levelMap.get(staffDetail.getStaffLevelId()));
		pageParaMap.put("comMap", comMap);
		pageParaMap.put("deptMap", deptMap);
		pageParaMap.put("levelMap", levelMap);
		pageParaMap.put("staffDetail", staffDetail);
		model.addAttribute("pageParaMap", pageParaMap);
		return "staffAdmin/staffModal";
	}
}
