/**
  * BaiduAPIService.java
  *
  * 分類　　：Honoka-com.honoka.service
  * 名称　　：
  * 説明　　：
  * 備考　　：
  * 作成　　：[日付] 2015/10/09 [氏名] 顧張融
  * 履歴：
  * [NO]  [日付]　        [Ver]　    [更新者]　         [内容]
  *  1   2015/10/09  V1      MBP)顧張融       初版。
  * Copyright (C) 2016, MBP All Rights Reserved.
  */
package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.entity.BaiduJson.BaiduPlaceResults;

@Service
public interface BaiduAPIService {

	/**
	 * 百度 API 地址解析。
	 *
	 * @param reqAddr
	 *            请求解析的地址
	 * @return the baidu json geocoding 返回 JSON 对象
	 * @throws Exception
	 *             the exception
	 */
	public BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception;

	public BaiduJsonPlace BaiduPlace(String reqKeyWord, Integer pageNum, String reqReqion) throws Exception;

}
