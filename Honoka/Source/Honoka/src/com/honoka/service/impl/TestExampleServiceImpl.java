/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.TestExampleMapper;
import com.honoka.entity.TestExample;
import com.honoka.service.TestExampleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestExampleServiceImpl implements TestExampleService {

    @Resource
    private TestExampleMapper testExampleMapper;

    @Override
    public List<TestExample> selectTestExample() {
        return testExampleMapper.selectTestExample();
    }

}
