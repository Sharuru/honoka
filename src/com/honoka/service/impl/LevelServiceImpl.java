/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.LevelMapper;
import com.honoka.entity.Level;
import com.honoka.service.LevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LevelServiceImpl implements LevelService {

    private static Map<String, String> levelMap = new HashMap<>();
    @Resource
    private LevelMapper levelMapper;

    @Override
    public Map<String, String> getLevelMap() {
        // 只设置一次字典
        if (levelMap.isEmpty()) {
            List<Level> levelList = levelMapper.getLevelMap();
            // 将列表转换成对应的字典
            for (Level aLevelList : levelList) {
                levelMap.put(aLevelList.getLevelId(), aLevelList.getLevelName());
            }
        }
        return levelMap;
    }

}
