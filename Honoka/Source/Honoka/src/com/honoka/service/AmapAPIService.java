package com.honoka.service;

import com.honoka.entity.AmapJson.AmapJsonGeocoding;
import org.springframework.stereotype.Service;

@Service
public interface AmapAPIService {

    /**
     * 高德 API 地址解析。
     *
     * @param reqAddr 请求解析的地址
     * @return 返回 JSON 对象
     * @throws Exception 异常
     */
    AmapJsonGeocoding AmapGeoCoding(String reqAddr) throws Exception;
}
