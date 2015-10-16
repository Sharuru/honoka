package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.Staff;

@Service
public interface StaffAdminService {
	
	Integer countStaffInfo();
	
	List<Staff> selectStaffInfoByPage(Integer page);

	Staff selectStaffDetailByStaffId(String staffId);
	
	void deleteStaffByStaffId(String staffId);
}
