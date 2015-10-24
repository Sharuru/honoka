package com.honoka.common;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class APIUtil {

	// 发起 Http 请求并捕获返回内容
	public static String readUrl(String urlString) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		return body;
	}
}
