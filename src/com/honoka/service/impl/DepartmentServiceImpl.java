package com.honoka.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.DepartmentMapper;
import com.honoka.entity.Department;
import com.honoka.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentMapper departmentMapper;
	private static Map<String, String> deptMap = new HashMap<String, String>();

	@Override
	public Map<String, String> getDeptMap() {
		// 只设置一次字典
		if (deptMap.isEmpty()) {
			List<Department> deptList = departmentMapper.getDeptmarmentMap();
			// 将列表转换成对应的字典
			for (int i = 0; i < deptList.size(); i++) {
				deptMap.put(deptList.get(i).getDeptId(), deptList.get(i).getDeptName());
			}
		}
		return deptMap;
	}

}
