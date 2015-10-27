/**
 * STAFF_INFO 表 POJO
 */
package com.honoka.entity;

public class Staff {
    // 记录 ID
    private Integer recordId;
    // 员工公司 ID
    private String staffComId;
    // 员工部门 ID
    private String staffDeptId;
    // 员工职称 ID
    private String staffLevelId;
    // 员工工号
    private String staffId;
    // 员工姓名
    private String staffName;
    // 员工电话
    private String staffTel;
    // 员工地址
    private String staffAddr;
    // 员工删除标记
    private String deleteFlag;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getStaffComId() {
        return staffComId;
    }

    public void setStaffComId(String staffComId) {
        this.staffComId = staffComId;
    }

    public String getStaffDeptId() {
        return staffDeptId;
    }

    public void setStaffDeptId(String staffDeptId) {
        this.staffDeptId = staffDeptId;
    }

    public String getStaffLevelId() {
        return staffLevelId;
    }

    public void setStaffLevelId(String staffLevelId) {
        this.staffLevelId = staffLevelId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(String staffTel) {
        this.staffTel = staffTel;
    }

    public String getStaffAddr() {
        return staffAddr;
    }

    public void setStaffAddr(String staffAddr) {
        this.staffAddr = staffAddr;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
