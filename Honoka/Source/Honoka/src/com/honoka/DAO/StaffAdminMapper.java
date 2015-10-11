package com.honoka.DAO;

import java.util.List;

import com.honoka.entity.Staff;

public interface StaffAdminMapper {
	
	List<Staff> selectStaffInfoWithPointByPage(Integer page);
}
