package com.honoka.service;

import org.springframework.stereotype.Service;

import com.honoka.entity.AmapJson.AmapJsonGeocoding;

@Service
public interface AmapAPIService {

	/**
	 * 高德 API 地址解析。
	 *
	 * @param reqAddr
	 *            请求解析的地址
	 * @return 返回 JSON 对象
	 * @throws Exception
	 *             异常
	 */
	public AmapJsonGeocoding AmapGeoCoding(String reqAddr) throws Exception;
}
