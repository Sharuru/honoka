package com.honoka.service.impl;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.honoka.common.APIUtil;
import com.honoka.entity.AmapJson.AmapJsonGeocoding;
import com.honoka.service.APIKeyService;
import com.honoka.service.AmapAPIService;

@Service
public class AmapAPIServiceImpl implements AmapAPIService {

	@Resource
	private APIKeyService apiKeyService;

	@Override
	public AmapJsonGeocoding AmapGeoCoding(String reqAddr) throws Exception {
		String json = null;
		try {
			json = APIUtil
					.readUrl("http://restapi.amap.com/v3/geocode/geo?address=" + URLEncoder.encode(reqAddr, "UTF-8")
							+ "&output=json&key=" + apiKeyService.selectUsableAPIKeyByProvider("AMAPWEB"));
			System.out.println("Get Amap Json: " + json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		AmapJsonGeocoding amapJson = gson.fromJson(json, AmapJsonGeocoding.class);
		return amapJson;
	}

}
