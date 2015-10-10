package com.honoka.service.impl;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import com.honoka.common.APIUtil;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;

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
}
