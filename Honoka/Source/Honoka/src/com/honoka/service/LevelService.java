package com.honoka.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface LevelService {
	Map<String,String> getLevelMap();
}
