/**
  * AmapAPIService.java
  *
  * 分類　　：Honoka-com.honoka.service
  * 名称　　：
  * 説明　　：
  * 備考　　：
  * 作成　　：[日付] 2015/10/10 [氏名] 顧張融
  * 履歴：
  * [NO]  [日付]　        [Ver]　    [更新者]　         [内容]
  *  1   2015/10/10  V1      MBP)顧張融       初版。
  * Copyright (C) 2016, MBP All Rights Reserved.
  */
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
	 * @return the amap json geocoding 返回 JSON 对象
	 * @throws Exception
	 *             the exception
	 */
	public AmapJsonGeocoding AmapGeoCoding(String reqAddr) throws Exception;
}
