package com.honoka.DAO;

import org.apache.ibatis.annotations.Param;

public interface PointMapper {

	void insertPointInfo(@Param("keyId") String keyId, @Param("baiduRecordLng") double baiduRecordLng,
			@Param("baiduRecordLat") double baiduReocordLat, @Param("amapRecordLng") double amapRecordLng,
			@Param("amapRecordLat") double amapRecordLat);
	
	void initMetroPoint();
}
