package com.honoka.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.honoka.DAO.TestExampleMapper;
import com.honoka.entity.TestExample;
import com.honoka.service.TestExampleService;

@Service
public class TestExampleServiceImpl implements TestExampleService {

	@Resource
	private TestExampleMapper testExampleMapper;
	
	@Override
	public List<TestExample> selectTestExample() {
		return testExampleMapper.selectTestExample();
	}

}
