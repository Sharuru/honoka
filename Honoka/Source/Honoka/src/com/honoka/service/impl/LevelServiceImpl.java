package com.honoka.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honoka.DAO.LevelMapper;
import com.honoka.entity.Level;
import com.honoka.service.LevelService;

@Service
public class LevelServiceImpl implements LevelService {

	@Resource
	private LevelMapper levelMapper;
	private static Map<String, String> levelMap = new HashMap<String, String>();

	@Override
	public Map<String, String> getLevelMap() {
		// 只设置一次字典
		if (levelMap.isEmpty()) {
			List<Level> levelList = levelMapper.getLevelMap();
			// 将列表转换成对应的字典
			for (int i = 0; i < levelList.size(); i++) {
				levelMap.put(levelList.get(i).getLevelId(), levelList.get(i).getLevelName());
			}
		}
		return levelMap;
	}

}
