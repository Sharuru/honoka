package com.honoka.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honoka.entity.POINT;

public interface PointMapper {

	void insertPointInfo(@Param("keyId") String keyId, @Param("baiduRecordLng") double baiduRecordLng,
			@Param("baiduRecordLat") double baiduReocordLat, @Param("amapRecordLng") double amapRecordLng,
			@Param("amapRecordLat") double amapRecordLat);
	
	void initMetroPoint();
	
	List<POINT> selectAllStaffPointInfo();
	
	List<POINT> selectPointInfoByKeyId(String keyId);
	
	void trimMetroPointData();
}
