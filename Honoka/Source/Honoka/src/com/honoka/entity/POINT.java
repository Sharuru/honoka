package com.honoka.entity;

public class POINT {
	private Integer recordId;
	private String keyId;
	private double baiduRecordLng;
	private double baiduRecordLat;
	private double amapRecordLng;
	private double amapRecordLat;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public double getBaiduRecordLng() {
		return baiduRecordLng;
	}

	public void setBaiduRecordLng(double baiduRecordLng) {
		this.baiduRecordLng = baiduRecordLng;
	}

	public double getBaiduRecordLat() {
		return baiduRecordLat;
	}

	public void setBaiduRecordLat(double baiduRecordLat) {
		this.baiduRecordLat = baiduRecordLat;
	}

	public double getAmapRecordLng() {
		return amapRecordLng;
	}

	public void setAmapRecordLng(double amapRecordLng) {
		this.amapRecordLng = amapRecordLng;
	}

	public double getAmapRecordLat() {
		return amapRecordLat;
	}

	public void setAmapRecordLat(double amapRecordLat) {
		this.amapRecordLat = amapRecordLat;
	}

}
