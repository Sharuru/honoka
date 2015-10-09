package com.honoka.service;

import org.springframework.stereotype.Service;

@Service
public interface APIKeyService {
	public String selectUsableAPIKeyByProvider(String provider);
}
