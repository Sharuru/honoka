/**
 * LEVEL_INFO 表 POJO
 */
package com.honoka.entity;

public class Level {
	// 记录 ID
	private Integer recordId;
	// 职称 ID
	private String levelId;
	// 职称名字
	private String levelName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
