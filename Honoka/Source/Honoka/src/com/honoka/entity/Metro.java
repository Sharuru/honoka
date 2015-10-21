/**
 * METRO_INFO 表 POJO
 */
package com.honoka.entity;

public class Metro {
	// 记录 ID
	private Integer recordId;
	// 线路名字
	private String lineName;
	// 站点 ID
	private String staId;
	// 站点名字
	private String staName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStaId() {
		return staId;
	}

	public void setStaId(String staId) {
		this.staId = staId;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

}
