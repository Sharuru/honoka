package com.honoka.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.StaffAdminMapper;
import com.honoka.entity.Staff;
import com.honoka.service.StaffAdminService;

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

}
