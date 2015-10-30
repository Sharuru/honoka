/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.PointMapper;
import com.honoka.entity.POINT;
import com.honoka.service.PointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    @Resource
    private PointMapper pointMapper;

    @Override
    public void insertPointInfo(String keyId, double baiduRecordLng, double baiduRecordLat, double amapRecordLng,
                                double amapRecordLat, String recordType) {
        pointMapper.insertPointInfo(keyId, baiduRecordLng, baiduRecordLat, amapRecordLng, amapRecordLat, recordType);

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

    @Override
    public List<POINT> selectPointInfoByPage(Integer reqPage) {
        return pointMapper.selectPointInfoByPage(reqPage);
    }

    @Override
    public Integer countPointInfoByRecordType(String recordType) {
        return pointMapper.countPointInfoByRecordType(recordType);
    }

}
