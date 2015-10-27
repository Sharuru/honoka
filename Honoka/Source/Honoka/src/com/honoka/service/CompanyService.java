package com.honoka.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CompanyService {

    /**
     * 获取 CompanyMap。
     *
     * @return company map
     */
    Map<String, String> getCompanyMap();
}
