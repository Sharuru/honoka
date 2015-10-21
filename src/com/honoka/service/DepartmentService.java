package com.honoka.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

	/**
	 * 获取 DeptMap。
	 *
	 * @return dept map
	 */
	Map<String, String> getDeptMap();
}
