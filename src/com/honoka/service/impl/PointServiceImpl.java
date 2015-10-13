package com.honoka.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.PointMapper;
import com.honoka.service.PointService;

@Service
public class PointServiceImpl implements PointService {

	@Resource
	private PointMapper pointMapper;

	@Override
	public void insertPointInfo(String keyId, double baiduRecordLng, double baiduReocordLat, double amapRecordLng,
			double amapRecordLat) {
		pointMapper.insertPointInfo(keyId, baiduRecordLng, baiduReocordLat, amapRecordLng, amapRecordLat);

	}

	@Override
	public void initMetroPoint() {
		pointMapper.initMetroPoint();		
	}

}
