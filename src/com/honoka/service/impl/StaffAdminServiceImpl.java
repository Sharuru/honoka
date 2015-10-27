/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.StaffAdminMapper;
import com.honoka.entity.Staff;
import com.honoka.service.StaffAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffAdminServiceImpl implements StaffAdminService {

    @Resource
    private StaffAdminMapper staffAdminMapper;

    @Override
    public Integer countStaffInfo() {
        return staffAdminMapper.countStaffInfo();
    }

    @Override
    public List<Staff> selectStaffInfoByPage(Integer page) {
        return staffAdminMapper.selectStaffInfoByPage(page);
    }

    @Override
    public Staff selectStaffDetailByStaffId(String staffId) {
        return staffAdminMapper.selectStaffDetailByStaffId(staffId);
    }

    @Override
    public void deleteStaffInfoByStaffId(String staffId) {
        staffAdminMapper.deleteStaffInfoByStaffId(staffId);
    }

    @Override
    public void updateStaffInfo(Staff staff) {
        staffAdminMapper.updateStaffInfo(staff);

    }

    @Override
    public void insertStaffInfo(Staff staff) {
        staffAdminMapper.insertStaffInfo(staff);
    }

}
