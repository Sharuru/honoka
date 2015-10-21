package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.Metro;

@Service
public interface MetroAdminService {

	/**
	 * 地铁站点信息条目数计算。
	 *
	 * @return 条目数
	 */
	Integer countMetroInfo();

	/**
	 * 根据页码获取地铁站点信息。
	 *
	 * @param page
	 *            页码
	 * @return 站点信息列表
	 */
	List<Metro> selectMetroInfoByPage(Integer page);

	/**
	 * 初始化 METRO_INFO 表。
	 */
	void initMetroInfo();

	/**
	 * 插入地铁站点信息。
	 *
	 * @param lineName
	 *            线路名
	 * @param staId
	 *            站点 ID
	 * @param staName
	 *            站点名
	 */
	void insertMetroInfo(String lineName, String staId, String staName);

	/**
	 * 获得线路名列表。
	 *
	 * @return 地铁线路名列表
	 */
	List<Metro> getMetroLineNameList();

	/**
	 * 通过线路名获得站点 ID。
	 *
	 * @param lineName
	 *            线路名
	 * @return 站点 ID 列表
	 */
	List<Metro> getMetroStationIdByLineName(String lineName);

	/**
	 * 通过站点 ID 获取站点名。
	 *
	 * @param staId
	 *            站点 ID
	 * @return 站点名
	 */
	String getMetroStationNameByStationId(String staId);
}
