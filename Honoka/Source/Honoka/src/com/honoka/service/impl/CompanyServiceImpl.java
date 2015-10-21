/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.CompanyMapper;
import com.honoka.entity.Company;
import com.honoka.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyMapper companyMapper;
	private static Map<String, String> comMap = new HashMap<String, String>();

	@Override
	public Map<String, String> getCompanyMap() {
		// 只设置一次字典
		if (comMap.isEmpty()) {
			comMap = new HashMap<String, String>();
			List<Company> comList = companyMapper.getCompanyMap();
			// 将列表转换成对应的字典
			for (int i = 0; i < comList.size(); i++) {
				comMap.put(comList.get(i).getComId(), comList.get(i).getComName());
			}
		}
		return comMap;
	}

}
