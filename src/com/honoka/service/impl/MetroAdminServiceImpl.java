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

	@Override
	public void insertMetroInfo(String lineName, String staId, String staName) {
		metroAdminMapper.insertMetroInfo(lineName, staId, staName);
	}

	@Override
	public void initMetroInfo() {
		metroAdminMapper.initMetroInfo();		
	}

	@Override
	public List<Metro> getMetroLineNameList() {
		return metroAdminMapper.getMetroLineNameList();
	}

	@Override
	public List<Metro> getMetroStationIdByLineName(String lineName) {
		return metroAdminMapper.getMetroStationIdByLineName(lineName);
	}
}
