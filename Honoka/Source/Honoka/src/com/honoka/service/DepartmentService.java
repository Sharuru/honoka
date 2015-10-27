package com.honoka.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DepartmentService {

    /**
     * 获取 DeptMap。
     *
     * @return dept map
     */
    Map<String, String> getDeptMap();
}
