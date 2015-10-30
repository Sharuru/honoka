package com.honoka.service;

import com.honoka.entity.POINT;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PointService {

    /**
     * 插入坐标信息。
     *
     * @param keyId          检索关键字
     * @param baiduRecordLng 百度记录纬度
     * @param baiduRecordLat 百度记录经度
     * @param amapRecordLng  高德记录纬度
     * @param amapRecordLat  高德记录经度
     * @param recordType     记录类型
     */
    void insertPointInfo(String keyId, double baiduRecordLng, double baiduRecordLat, double amapRecordLng,
                         double amapRecordLat, String recordType);

    /**
     * 初始化 POINT_INFO 中的站点信息。
     */
    void initMetroPoint();

    /**
     * 获得所有有效员工的坐标信息。
     *
     * @return 员工坐标信息列表
     */
    List<POINT> selectAllStaffPointInfo();

    /**
     * 通过检索关键字获取坐标信息。
     *
     * @param keyId 检索关键字
     * @return 坐标 POJO
     */
    POINT selectPointInfoByKeyId(String keyId);

    /**
     * 移除重复站点坐标信息。
     */
    // API 返回信息有老数据，但是 UID 相同，故需移除
    void trimMetroPointData();

    /**
     * 通过检索关键字更新坐标信息。
     *
     * @param point 坐标 POJO
     */
    void updatePointInfoByKeyId(POINT point);

    /**
     * 根据检索关键字删除坐标信息
     *
     * @param keyId 检索关键字
     */
    void deletePointInfoByKeyId(String keyId);


    /**
     * 根据页数获得坐标信息
     *
     * @param reqPage 需要页数限制
     * @return 坐标列表
     */
    List<POINT> selectPointInfoByPage(Integer reqPage);


    /**
     * 根据记录类型统计条目记录数
     * @param recordType 记录类型
     * @return 条目数
     */
    Integer countPointInfoByRecordType(String recordType);
}
