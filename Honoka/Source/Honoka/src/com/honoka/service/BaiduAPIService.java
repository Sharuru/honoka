/**
 * BaiduAPIService.java
 * <p>
 * 分類　　：Honoka-com.honoka.service
 * 名称　　：
 * 説明　　：
 * 備考　　：
 * 作成　　：[日付] 2015/10/22 [氏名] 顧張融
 * 履歴：
 * [NO]  [日付]　        [Ver]　    [更新者]　         [内容]
 * 1   2015/10/22  V1      MBP)顧張融       初版。
 * Copyright (C) 2016, MBP All Rights Reserved.
 */
package com.honoka.service;

import com.honoka.entity.BaiduJson;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import org.springframework.stereotype.Service;

@Service
public interface BaiduAPIService {

    /**
     * 百度 API 地址解析。
     *
     * @param reqAddr 请求解析的地址
     * @return 返回 JSON 对象
     * @throws Exception 异常
     */
    BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception;

    /**
     * 百度 API 位置搜索。
     *
     * @param reqKeyWord 搜索关键字
     * @param pageSize   页面尺寸
     * @param pageNum    要求页码
     * @param reqReqion  搜索区域
     * @return 返回 JSON 对象
     * @throws Exception 异常
     */
    BaiduJsonPlace BaiduPlace(String reqKeyWord, Integer pageSize, Integer pageNum, String reqReqion)
            throws Exception;

    /**
     * 百度 Direction 驾车线路。
     *
     * @return 返回 JSON 对象
     */
    BaiduJson.BaiduJsonDirectionDriving BaiduDirectionDriving(String oLat, String oLng, String dLat, String dLng, String origin_region, String destination_region) throws Exception;

    BaiduJson.BaiduJsonDirectionTransit BaiduDirectionTransit(String oLat, String oLng, String dLat, String dLng, String origin_region, String destination_region) throws Exception;
}
