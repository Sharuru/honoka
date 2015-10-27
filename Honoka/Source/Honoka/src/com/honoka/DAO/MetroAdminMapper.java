/**
 * 映射类功能注释请查看对应同名 Service 类
 */
package com.honoka.DAO;

import com.honoka.entity.Metro;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetroAdminMapper {
    Integer countMetroInfo();

    List<Metro> selectMetroInfoByPage(Integer page);

    void initMetroInfo();

    void insertMetroInfo(@Param("lineName") String lineName, @Param("staId") String staId,
                         @Param("staName") String staName);

    List<Metro> getMetroLineNameList();

    List<Metro> getMetroStationIdByLineName(String lineName);

    String getMetroStationNameByStationId(String staId);
}
