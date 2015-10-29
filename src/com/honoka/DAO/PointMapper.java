/**
 * 映射类功能注释请查看对应同名 Service 类
 */
package com.honoka.DAO;

import com.honoka.entity.POINT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointMapper {
    void insertPointInfo(@Param("keyId") String keyId, @Param("baiduRecordLng") double baiduRecordLng,
                         @Param("baiduRecordLat") double baiduRecordLat, @Param("amapRecordLng") double amapRecordLng,
                         @Param("amapRecordLat") double amapRecordLat, @Param("recordType") String recordType);

    void initMetroPoint();

    List<POINT> selectAllStaffPointInfo();

    POINT selectPointInfoByKeyId(String keyId);

    void trimMetroPointData();

    void updatePointInfoByKeyId(POINT point);

    void deletePointInfoByKeyId(String keyId);
}
