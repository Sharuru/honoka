package com.honoka.entity;

import java.util.List;

public class BaiduJson {
	public static class BaiduJsonGeocoding {
		// 状态
		Integer status;
		// 结果
		GeocodingResult result;
		// 异常信息
		String msg;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public GeocodingResult getResult() {
			return result;
		}

		public void setResult(GeocodingResult result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static class GeocodingResult {
		// 附加信息，是否精确查找
		Integer precise;
		// 可信度
		Integer confidence;
		// 地址类型
		String level;
		// 坐标
		GeocodingLocation location;

		public Integer getPrecise() {
			return precise;
		}

		public void setPrecise(Integer precise) {
			this.precise = precise;
		}

		public Integer getConfidence() {
			return confidence;
		}

		public void setConfidence(Integer confidence) {
			this.confidence = confidence;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public GeocodingLocation getLocation() {
			return location;
		}

		public void setLocation(GeocodingLocation location) {
			this.location = location;
		}

	}

	public static class GeocodingLocation {
		// 纬度值
		Float lat;
		// 经度值
		Float lng;

		public Float getLat() {
			return lat;
		}

		public void setLat(Float lat) {
			this.lat = lat;
		}

		public Float getLng() {
			return lng;
		}

		public void setLng(Float lng) {
			this.lng = lng;
		}

	}

	public static class BaiduJsonPlace {
		Integer status;
		String message;
		Integer total;
		List<PlaceResults> results;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public List<PlaceResults> getResults() {
			return results;
		}

		public void setResults(List<PlaceResults> results) {
			this.results = results;
		}

	}

	public static class PlaceResults {
		String name;
		PlaceLocation location;
		String address;
		String street_id;
		Integer detail;
		String uid;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public PlaceLocation getLocation() {
			return location;
		}

		public void setLocation(PlaceLocation location) {
			this.location = location;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getStreet_id() {
			return street_id;
		}

		public void setStreet_id(String street_id) {
			this.street_id = street_id;
		}

		public Integer getDetail() {
			return detail;
		}

		public void setDetail(Integer detail) {
			this.detail = detail;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

	}

	public static class PlaceLocation {
		float lat;
		float lng;

		public float getLat() {
			return lat;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}

		public float getLng() {
			return lng;
		}

		public void setLng(float lng) {
			this.lng = lng;
		}

	}

}
