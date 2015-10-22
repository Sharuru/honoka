/**
 * 百度地图返回 Json POJO
 */
package com.honoka.entity;

import java.util.List;

public class BaiduJson {
	// 百度地图地址解析 POJO
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

	// 地址解析结果
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

	// 地址解析坐标
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

	// 百度地图位置搜索 POJO
	public static class BaiduJsonPlace {
		// 状态
		Integer status;
		// 信息
		String message;
		// 数据总数
		Integer total;
		// 结果
		List<BaiduPlaceResults> results;

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

		public List<BaiduPlaceResults> getResults() {
			return results;
		}

		public void setResults(List<BaiduPlaceResults> results) {
			this.results = results;
		}

	}

	// 位置搜索结果
	public static class BaiduPlaceResults {
		// POI 名
		String name;
		// 位置
		PlaceLocation location;
		// 地址
		String address;
		// 街道 ID
		String street_id;
		// 联系电话
		String telephone;
		// 详细级数
		Integer detail;
		// 记录 ID
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

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
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

	// 位置搜索位置
	public static class PlaceLocation {
		// 纬度值
		float lat;
		// 经度值
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

	// Direction API POJO
	public static class BaiduJsonDirectionDriving {
		// 状态码
		Integer stauts;
		// 状态码对应信息
		String message;
		// 返回数据类型
		Integer type;
		// 版权信息
		String info;
		// 返回的结果
		BaiduDirectionDrivingResult result;

		public Integer getStauts() {
			return stauts;
		}

		public void setStauts(Integer stauts) {
			this.stauts = stauts;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public BaiduDirectionDrivingResult getResult() {
			return result;
		}

		public void setResult(BaiduDirectionDrivingResult result) {
			this.result = result;
		}

	}

	// Direction API 返回的结果
	public static class BaiduDirectionDrivingResult {
		// 路径
		BaiduDirectionDrivingRoutes[] routes;
		// 其余元素暂缓

		public BaiduDirectionDrivingRoutes[] getRoutes() {
			return routes;
		}

		public void setRoutes(BaiduDirectionDrivingRoutes[] routes) {
			this.routes = routes;
		}

	}

	// 路径详情
	public static class BaiduDirectionDrivingRoutes {
		// 方案距离
		Integer distance;
		// 线路耗时
		Integer duration;

		public Integer getDistance() {
			return distance;
		}

		public void setDistance(Integer distance) {
			this.distance = distance;
		}

		public Integer getDuration() {
			return duration;
		}

		public void setDuration(Integer duration) {
			this.duration = duration;
		}

	}

}
