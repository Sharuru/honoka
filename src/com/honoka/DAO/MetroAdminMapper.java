package com.honoka.DAO;

import java.util.List;

import com.honoka.entity.Metro;

public interface MetroAdminMapper {
	Integer countMetroInfo();

	List<Metro> selectMetroInfoByPage(Integer page);
}
