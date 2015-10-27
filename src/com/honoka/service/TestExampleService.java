package com.honoka.service;

import com.honoka.entity.TestExample;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TestExampleService {

    /**
     * 测试
     *
     * @return the list
     */
    List<TestExample> selectTestExample();

}
