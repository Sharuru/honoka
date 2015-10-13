package com.honoka.service;

import org.springframework.stereotype.Service;

@Service
public interface PointService {
	void insertPointInfo(String keyId, double baiduRecordLng, double baiduReocordLat, double amapRecordLng, double amapRecordLat);
	
	//TODO：临时措施，需要重构
	void initMetroPoint();
}
