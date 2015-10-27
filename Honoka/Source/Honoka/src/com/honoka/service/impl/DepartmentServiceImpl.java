/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.honoka.DAO.DepartmentMapper;
import com.honoka.entity.Department;
import com.honoka.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static Map<String, String> deptMap = new HashMap<>();
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Map<String, String> getDeptMap() {
        // 只在字典为空时设置一次字典
        if (deptMap.isEmpty()) {
            List<Department> deptList = departmentMapper.getDeptmarmentMap();
            // 将列表转换成对应的字典
            for (Department aDeptList : deptList) {
                deptMap.put(aDeptList.getDeptId(), aDeptList.getDeptName());
            }
        }
        return deptMap;
    }

}
