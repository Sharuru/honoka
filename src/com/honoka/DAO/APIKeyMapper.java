package com.honoka.DAO;

public interface APIKeyMapper {
	String selectUsableAPIKeyByProvider(String provider);
	
	Integer selectAmountByAPIKey(String key);
}