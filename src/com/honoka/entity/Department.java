/**
 * DEPT_INFO 表 POJO
 */
package com.honoka.entity;

public class Department {
	// 记录 ID
	private Integer recordId;
	// 部门 ID
	private String deptId;
	// 部门名字
	private String deptName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
