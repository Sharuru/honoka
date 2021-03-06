/**
 * 员工数据管理 Controller
 */
package com.honoka.web.controller;

import com.honoka.entity.POINT;
import com.honoka.entity.Staff;
import com.honoka.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffAdminController {

    @Resource
    private StaffAdminService staffAdminService;
    @Resource
    private CompanyService companyService;
    @Qualifier("departmentServiceImpl")
    @Resource
    private DepartmentService departmentService;
    @Resource
    private LevelService levelService;
    @Resource
    private PointService pointService;

    // 员工数据管理画面
    @RequestMapping(value = "/staffAdmin&reqPage={reqPage}", method = RequestMethod.GET)
    public String staffAdminRouter(ModelMap model, @PathVariable Integer reqPage) {
        System.out.println("In /staffAdmin&reqPage=" + reqPage);
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        System.out.println("currPage = " + reqPage);
        pageParaMap.put("currPage", reqPage);
        pageParaMap.put("totalCount", staffAdminService.countStaffInfo());
        List<Staff> staffList = staffAdminService.selectStaffInfoByPage(reqPage);
        // 翻译
        Map<String, String> comMap = companyService.getCompanyMap();
        Map<String, String> deptMap = departmentService.getDeptMap();
        Map<String, String> levelMap = levelService.getLevelMap();
        for (Staff currStaff : staffList) {
            currStaff.setStaffComId(comMap.get(currStaff.getStaffComId()));
            currStaff.setStaffDeptId(deptMap.get(currStaff.getStaffDeptId()));
            currStaff.setStaffLevelId(levelMap.get(currStaff.getStaffLevelId()));
        }
        pageParaMap.put("staffInfoList", staffList);
        model.addAttribute("pageParaMap", pageParaMap);
        return "staffAdmin/staffMain";
    }

    // 员工数据新增模态
    @RequestMapping(value = "/addStaffInfo", method = RequestMethod.GET)
    public String AddStaffInfoRouter(ModelMap model) {
        System.out.println("In /addStaffInfo");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        // 获取对应 ID 字典以设置下拉列表
        Map<String, String> comMap = companyService.getCompanyMap();
        Map<String, String> deptMap = departmentService.getDeptMap();
        Map<String, String> levelMap = levelService.getLevelMap();
        pageParaMap.put("comMap", comMap);
        pageParaMap.put("deptMap", deptMap);
        pageParaMap.put("levelMap", levelMap);
        model.addAttribute("pageParaMap", pageParaMap);
        return "staffAdmin/addStaffModal";
    }

    // 员工数据详情
    @RequestMapping(value = "/staffDetail&reqStaffId={reqId}", method = RequestMethod.GET)
    public String staffDetailRouter(ModelMap model, @PathVariable String reqId) {
        System.out.println("In /staffDetail&reqStaffId=" + reqId);
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        pageParaMap.put("totalCount", staffAdminService.countStaffInfo());
        Staff staffDetail = staffAdminService.selectStaffDetailByStaffId(reqId);
        POINT reqStaffPoint = pointService.selectPointInfoByKeyId(reqId);
        // 获取对应 ID 字典以设置下拉列表
        Map<String, String> comMap = companyService.getCompanyMap();
        Map<String, String> deptMap = departmentService.getDeptMap();
        Map<String, String> levelMap = levelService.getLevelMap();
        pageParaMap.put("comMap", comMap);
        pageParaMap.put("deptMap", deptMap);
        pageParaMap.put("levelMap", levelMap);
        pageParaMap.put("staffDetail", staffDetail);
        pageParaMap.put("staffPoint", reqStaffPoint);
        model.addAttribute("pageParaMap", pageParaMap);
        return "staffAdmin/staffDetailModal";
    }

    // 员工数据新增
    @RequestMapping(value = "/reqAddStaffInfo", method = RequestMethod.POST)
    public String ReqAddStaffInfoRouter(String staffId, String staffName, String staffComId,
                                        String staffDeptId, String staffLevelId, String staffTel, String staffAddr, String staffPointLng,
                                        String staffPointLat) {
        System.out.println("In /reqAddStaffInfo");
        // 新建员工 bean
        Staff staff = new Staff();
        staff.setStaffId(staffId);
        staff.setStaffName(staffName);
        staff.setStaffComId(staffComId);
        staff.setStaffDeptId(staffDeptId);
        staff.setStaffLevelId(staffLevelId);
        staff.setStaffTel(staffTel);
        staff.setStaffAddr(staffAddr);
        // 插入员工信息
        staffAdminService.insertStaffInfo(staff);
        // 插入对应坐标信息
        pointService.insertPointInfo(staffId, Double.parseDouble(staffPointLng), Double.parseDouble(staffPointLat), 0.0,
                0.0, "STAFF");
        return "staffAdmin/staffMain";
    }

    // 员工数据更新
    @RequestMapping(value = "/reqUpdateStaffInfo", method = RequestMethod.POST)
    public String ReqUpdateStaffInfoRouter(String staffId, String staffName, String staffComId,
                                           String staffDeptId, String staffLevelId, String staffTel, String staffAddr, String staffPointLng,
                                           String staffPointLat) {
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

    // 员工数据删除
    @RequestMapping(value = "/reqDeleteStaffInfo", method = RequestMethod.POST)
    public String ReqDeleteStaffInfoRouter(String staffId) {
        System.out.println("In /reqDeleteStaffInfo");
        staffAdminService.deleteStaffInfoByStaffId(staffId);
        pointService.deletePointInfoByKeyId(staffId);
        return "staffAdmin/staffMain";
    }
}
