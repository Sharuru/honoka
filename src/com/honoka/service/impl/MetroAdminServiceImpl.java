package com.honoka.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.MetroAdminMapper;
import com.honoka.entity.Metro;
import com.honoka.service.MetroAdminService;

@Service
public class MetroAdminServiceImpl implements MetroAdminService {

	@Resource
	private MetroAdminMapper metroAdminMapper;

	@Override
	public List<Metro> selectMetroInfoByPage(Integer page) {
		return metroAdminMapper.selectMetroInfoByPage(page);
	}

	@Override
	public Integer countMetroInfo() {
		return metroAdminMapper.countMetroInfo();
	}
}
