package com.honoka.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LevelService {

    /**
     * 获取 LevelMap。
     *
     * @return level map
     */
    Map<String, String> getLevelMap();
}
