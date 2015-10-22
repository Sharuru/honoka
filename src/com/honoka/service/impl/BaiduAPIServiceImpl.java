/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import com.honoka.common.APIUtil;
import com.honoka.entity.BaiduJson.BaiduJsonDirectionDriving;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;

@Service
public class BaiduAPIServiceImpl implements BaiduAPIService {

	@Resource
	private APIKeyService apiKeyService;

	@Override
	public BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception {
		String json = null;
		// 拼接请求字符串获得内容
		json = APIUtil.readUrl("http://api.map.baidu.com/geocoder/v2/?address=" + URLEncoder.encode(reqAddr, "UTF-8")
				+ "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		// System.out.println("Get Baidu Json: " + json);
		Gson gson = new Gson();
		// Json to POJO
		BaiduJsonGeocoding bdJson = gson.fromJson(json, BaiduJsonGeocoding.class);
		return bdJson;
	}

	@Override
	public BaiduJsonPlace BaiduPlace(String reqKeyWord, Integer pageSize, Integer pageNum, String reqRegion) throws Exception {
		String json = null;
		json = APIUtil.readUrl(
				"http://api.map.baidu.com/place/v2/search?ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU")
						+ "&output=json&query=" + URLEncoder.encode(reqKeyWord, "UTF-8") + "&page_size=" + pageSize + "&page_num="
						+ pageNum + "&scope=1&region=" + URLEncoder.encode(reqRegion, "UTF-8"));
		Gson gson = new Gson();
		BaiduJsonPlace bdJson = gson.fromJson(json, BaiduJsonPlace.class);
		return bdJson;
	}

	@Override
	public BaiduJsonDirectionDriving BaiduDirectionDriving(String reqKeyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
