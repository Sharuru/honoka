package com.honoka.service;

import org.springframework.stereotype.Service;

import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;

@Service
public interface BaiduAPIService {

	/**
	 * 百度 API 地址解析。
	 *
	 * @param reqAddr
	 *            请求解析的地址
	 * @return 返回 JSON 对象
	 * @throws Exception
	 *             异常
	 */
	public BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception;

	/**
	 * 百度 API 位置搜索。
	 *
	 * @param reqKeyWord
	 *            搜索关键字
	 * @param pageNum
	 *            要求页码
	 * @param reqReqion
	 *            搜索区域
	 * @return 返回 JSON 对象
	 * @throws Exception
	 *             异常
	 */
	public BaiduJsonPlace BaiduPlace(String reqKeyWord, Integer pageNum, String reqReqion) throws Exception;

}
