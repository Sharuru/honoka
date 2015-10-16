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

import com.honoka.entity.POINT;
import com.honoka.entity.Staff;
import com.honoka.service.CompanyService;
import com.honoka.service.DepartmentService;
import com.honoka.service.LevelService;
import com.honoka.service.PointService;
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
	@Resource
	private PointService pointService;

	// 员工数据管理画面
	@RequestMapping(value = "/staffAdmin&reqPage={reqPage}", method = RequestMethod.GET)
	public String staffAdminRouter(ModelMap model, @PathVariable Integer reqPage) {
		System.out.println("In //staffAdmin&reqPage=" + reqPage);
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		System.out.println("currPage = " + reqPage);
		pageParaMap.put("currPage", reqPage);
		pageParaMap.put("totalCount", staffAdminService.countStaffInfo());
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
		pageParaMap.put("staffInfoList", staffList);
		model.addAttribute("pageParaMap", pageParaMap);
		return "staffAdmin/staffMain";
	}

	// 员工数据详情
	@RequestMapping(value = "/staffDetail&reqStaffId={reqId}", method = RequestMethod.GET)
	public String staffDetailRouter(ModelMap model, @PathVariable String reqId) {
		System.out.println("In /staffDetail&reqStaffId=" + reqId);
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		pageParaMap.put("totalCount", staffAdminService.countStaffInfo());
		Staff staffDetail = staffAdminService.selectStaffDetailByStaffId(reqId);
		POINT reqStaffPoint = pointService.selectPointInfoByKeyId(reqId);
		// 获取对应 ID 字典以设置下拉列表
		Map<String, String> comMap = companyService.getCompanyMap();
		Map<String, String> deptMap = departmantService.getDeptMap();
		Map<String, String> levelMap = levelService.getLevelMap();
		pageParaMap.put("comMap", comMap);
		pageParaMap.put("deptMap", deptMap);
		pageParaMap.put("levelMap", levelMap);
		pageParaMap.put("staffDetail", staffDetail);
		pageParaMap.put("staffPoint", reqStaffPoint);
		model.addAttribute("pageParaMap", pageParaMap);
		return "staffAdmin/staffDetailModal";
	}

	// 员工数据更新
	@RequestMapping(value="/reqUpdateStaffInfo", method=RequestMethod.POST)
	public String ReqUpdateStaffInfoRouter(ModelMap model, String staffId, String staffName, String staffComId, String staffDeptId, String staffLevelId, String staffTel,
			String staffAddr, String staffPointLng, String staffPointLat){
		System.out.println("In /reqUpdateStaffInfo");
		Staff staff = new Staff();
		staff.setStaffId(staffId);
		staff.setStaffName(staffName);
		staff.setStaffComId(staffComId);
		staff.setStaffDeptId(staffDeptId);
		staff.setStaffLevelId(staffLevelId);
		staff.setStaffTel(staffTel);
		staff.setStaffAddr(staffAddr);
		POINT point = new POINT();
		point.setKeyId(staffId);
		point.setBaiduRecordLng(Double.parseDouble(staffPointLng));
		point.setBaiduRecordLat(Double.parseDouble(staffPointLat));
		staffAdminService.updateStaffInfo(staff);
		pointService.updatePointInfoByKeyId(point);
		return "staffAdmin/staffMain";
	}
}
