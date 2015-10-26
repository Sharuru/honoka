/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.APIKeyMapper;
import com.honoka.service.APIKeyService;

@Service
public class APIKeyServiceImpl implements APIKeyService {

	@Resource
	private APIKeyMapper apiKeyMapper;

	@Override
	public String selectUsableAPIKeyByProvider(String provider) {
		//TODO：缓存 key
        //return "CqTmA6FQhjfbDQl2dQMLIBnu";
		return apiKeyMapper.selectUsableAPIKeyByProvider(provider);
	}

	@Override
	public Integer selectAmountByAPIKey(String key) {
		return apiKeyMapper.selectAmountByAPIKey(key);
	}

}
