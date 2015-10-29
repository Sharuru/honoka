/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.APIKeyMapper;
import com.honoka.service.APIKeyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class APIKeyServiceImpl implements APIKeyService {

    private static String currBaiduAPIKey;
    private static String currAmapWebAPIKey;
    @Resource
    private APIKeyMapper apiKeyMapper;

    @Override
    public String selectUsableAPIKeyByProvider(String provider) {
        // TODO：缓存 KEY，未来根据次数优选或者返回 KEY 集（并发）
        switch (provider) {
            case "BAIDU":
                if (currBaiduAPIKey == null) {
                    currBaiduAPIKey = apiKeyMapper.selectUsableAPIKeyByProvider(provider);
                }
                return currBaiduAPIKey;
            case "AMAPWEB":
                if (currAmapWebAPIKey == null) {
                    currAmapWebAPIKey = apiKeyMapper.selectUsableAPIKeyByProvider(provider);
                }
                return currAmapWebAPIKey;
            default:
                return "Error";
        }
    }

    @Override
    public Integer selectAmountByAPIKey(String key) {
        return apiKeyMapper.selectAmountByAPIKey(key);
    }

}
