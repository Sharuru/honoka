package com.honoka.service;

import org.springframework.stereotype.Service;

@Service
public interface APIKeyService {

    /**
     * 通过提供方字段获取可用 API KEY。
     *
     * @param provider 提供方表示
     * @return API KEY
     */
    String selectUsableAPIKeyByProvider(String provider);

    /**
     * 通过 API KEY 获取使用总量。
     *
     * @param key API KEY
     * @return 使用量
     */
    Integer selectAmountByAPIKey(String key);
}
