package com.honoka.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honoka.entity.Metro;

@Service
public interface MetroAdminService {
	
	Integer countMetroInfo();
	List<Metro> selectMetroInfoByPage(Integer page);
}
