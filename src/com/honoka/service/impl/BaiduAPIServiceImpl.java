package com.honoka.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.honoka.service.BaiduAPIService;

@Service
public class BaiduAPIServiceImpl implements BaiduAPIService{

	
	@Override
	public String BaiduGeoCoding(String reqAddr){
		return reqAddr;

	}

}
