/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.PointMapper;
import com.honoka.entity.POINT;
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

	@Override
	public List<POINT> selectAllStaffPointInfo() {
		return pointMapper.selectAllStaffPointInfo();
	}

	@Override
	public POINT selectPointInfoByKeyId(String keyId) {
		return pointMapper.selectPointInfoByKeyId(keyId);
	}

	@Override
	public void trimMetroPointData() {
		pointMapper.trimMetroPointData();

	}

	@Override
	public void updatePointInfoByKeyId(POINT point) {
		pointMapper.updatePointInfoByKeyId(point);
	}

	@Override
	public void deletePointInfoByKeyId(String keyId) {
		pointMapper.deletePointInfoByKeyId(keyId);
	}

}
