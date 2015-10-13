package com.honoka.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honoka.entity.Metro;

public interface MetroAdminMapper {
	Integer countMetroInfo();

	List<Metro> selectMetroInfoByPage(Integer page);
	
	void initMetroInfo();
	
	void insertMetroInfo(@Param("lineName") String lineName,@Param("staId")  String staId, @Param("staName") String staName);
}
