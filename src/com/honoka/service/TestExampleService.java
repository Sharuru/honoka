package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.TestExample;


@Service
public interface TestExampleService {
	
	/**
	 * 测试
	 *
	 * @return the list
	 */
	public List<TestExample> selectTestExample();
	
}
