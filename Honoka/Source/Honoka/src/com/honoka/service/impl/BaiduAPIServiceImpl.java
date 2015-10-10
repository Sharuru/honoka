package com.honoka.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import com.honoka.common.APIUtil;

@Service
public class BaiduAPIServiceImpl implements BaiduAPIService {

	@Resource
	private APIKeyService apiKeyService;

	@Override
	public BaiduJson BaiduGeoCoding(String reqAddr) {
		String json = null;
		try {
			json = APIUtil
					.readUrl("http://api.map.baidu.com/geocoder/v2/?address=" + URLEncoder.encode(reqAddr, "UTF-8")
							+ "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
			System.out.println("Get Baidu Json: " + json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		BaiduJson bdJson = gson.fromJson(json, BaiduJson.class);
		return bdJson;
	}

	// TODO: Baidu Geocoding Json Class
	public static class BaiduJson {
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
