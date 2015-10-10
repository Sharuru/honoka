package com.honoka.entity;

public class BaiduJson {
	public static class BaiduJsonGeocoding {
		// 状态
		Integer status;
		// 结果
		Result result;
		// 异常信息
		String msg;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static class Result {
		// 附加信息，是否精确查找
		Integer precise;
		// 可信度
		Integer confidence;
		// 地址类型
		String level;
		// 坐标
		Location location;

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

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

	}

	public static class Location {
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
}
