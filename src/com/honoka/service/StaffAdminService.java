package com.honoka.service;

import com.honoka.entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffAdminService {

    /**
     * 有效员工条目数计算。
     *
     * @return 条目数
     */
    Integer countStaffInfo();

    /**
     * 根据页码获得员工信息。
     *
     * @param page 页码
     * @return 员工信息 POJO 列表
     */
    List<Staff> selectStaffInfoByPage(Integer page);

    /**
     * 根据工号获得员工详细信息。
     *
     * @param staffId 员工工号
     * @return 员工 POJO
     */
    Staff selectStaffDetailByStaffId(String staffId);

    /**
     * 根据工号删除员工信息。
     *
     * @param staffId 员工工号
     */
    void deleteStaffInfoByStaffId(String staffId);

    /**
     * 更新员工信息。
     *
     * @param staff 员工 POJO
     */
    void updateStaffInfo(Staff staff);

    /**
     * 插入员工信息。
     *
     * @param staff 员工 POJO
     */
    void insertStaffInfo(Staff staff);
}
