/**
 * 映射类功能注释请查看对应同名 Service 类
 */
package com.honoka.DAO;

import com.honoka.entity.Staff;

import java.util.List;

public interface StaffAdminMapper {
    Integer countStaffInfo();

    List<Staff> selectStaffInfoByPage(Integer page);

    Staff selectStaffDetailByStaffId(String staffId);

    void deleteStaffInfoByStaffId(String staffId);

    void updateStaffInfo(Staff staff);

    void insertStaffInfo(Staff staff);
}
