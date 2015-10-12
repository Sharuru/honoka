package com.honoka.service.impl;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import com.honoka.common.APIUtil;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.entity.BaiduJson.PlaceResults;

@Service
public class BaiduAPIServiceImpl implements BaiduAPIService {

	@Resource
	private APIKeyService apiKeyService;

	@Override
	public BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception {
		String json = null;
		json = APIUtil.readUrl("http://api.map.baidu.com/geocoder/v2/?address=" + URLEncoder.encode(reqAddr, "UTF-8")
				+ "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		System.out.println("Get Baidu Json: " + json);
		Gson gson = new Gson();
		BaiduJsonGeocoding bdJson = gson.fromJson(json, BaiduJsonGeocoding.class);
		return bdJson;
	}

	@Override
	public List<PlaceResults> BaiduPlace(String reqKeyWord) throws Exception {
		String json = null;
		json = APIUtil
				.readUrl("http://api.map.baidu.com/place/v2/search?ak="
						+ apiKeyService.selectUsableAPIKeyByProvider("BAIDU")
				+ "&output=json&query=" + URLEncoder.encode(reqKeyWord, "UTF-8")
				+ "&page_size=20&page_num=0&scope=1&region=" + URLEncoder.encode("上海市", "UTF-8"));
		System.out.println("Get Baidu Json: " + json);
		Gson gson = new Gson();
		BaiduJsonPlace bdJson = gson.fromJson(json, BaiduJsonPlace.class);
		return bdJson.getResults();
	}
}
