/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.CompanyMapper;
import com.honoka.entity.Company;
import com.honoka.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static Map<String, String> comMap = new HashMap<>();
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public Map<String, String> getCompanyMap() {
        // 只设置一次字典
        if (comMap.isEmpty()) {
            comMap = new HashMap<>();
            List<Company> comList = companyMapper.getCompanyMap();
            // 将列表转换成对应的字典
            for (Company aComList : comList) {
                comMap.put(aComList.getComId(), aComList.getComName());
            }
        }
        return comMap;
    }

}
