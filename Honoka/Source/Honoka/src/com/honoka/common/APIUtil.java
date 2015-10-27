package com.honoka.common;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class APIUtil {

    /**
     * 发起 Http 请求并捕获返回内容
     *
     * @param urlString URL 地址
     * @return 响应字符串
     * @throws Exception
     */
    public static String readUrl(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        return IOUtils.toString(in, encoding);
    }
}
