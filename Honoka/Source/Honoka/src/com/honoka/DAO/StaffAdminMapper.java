package com.honoka.DAO;

import java.util.List;

import com.honoka.entity.Staff;

public interface StaffAdminMapper {
	
	Integer countStaffInfo();
	
	List<Staff> selectStaffInfoByPage(Integer page);
	
	Staff selectStaffDetailByStaffId(String staffId);
	
	void deleteStaffInfoByStaffId(String staffId);
	
	void updateStaffInfo(Staff staff);
	
	void insertStaffInfo(Staff staff);
}
