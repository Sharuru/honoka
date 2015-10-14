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
		return apiKeyMapper.selectUsableAPIKeyByProvider(provider);
	}

	@Override
	public Integer selectAmountByAPIKey(String key) {
		return apiKeyMapper.selectAmountByAPIKey(key);
	}

}