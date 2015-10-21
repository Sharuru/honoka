package com.honoka.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface LevelService {

	/**
	 * 获取 LevelMap。
	 *
	 * @return level map
	 */
	Map<String, String> getLevelMap();
}
