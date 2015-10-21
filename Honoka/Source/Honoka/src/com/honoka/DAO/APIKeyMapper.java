/**
 * 映射类功能注释请查看对应同名 Service 类
 */
package com.honoka.DAO;

public interface APIKeyMapper {
	String selectUsableAPIKeyByProvider(String provider);

	Integer selectAmountByAPIKey(String key);
}