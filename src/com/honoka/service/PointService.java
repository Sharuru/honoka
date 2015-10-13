package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.POINT;

@Service
public interface PointService {
	void insertPointInfo(String keyId, double baiduRecordLng, double baiduReocordLat, double amapRecordLng, double amapRecordLat);
	
	//TODO：临时措施，需要重构
	void initMetroPoint();
	
	List<POINT> selectAllStaffPointInfo();
}
