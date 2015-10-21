package com.honoka.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface CompanyService {

	/**
	 * 获取 CompanyMap。
	 *
	 * @return company map
	 */
	Map<String, String> getCompanyMap();
}
