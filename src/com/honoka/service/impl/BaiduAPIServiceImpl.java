/**
 * 服务实现类功能注释请查看对应同名 Service 类
 */
package com.honoka.service.impl;

import com.google.gson.Gson;
import com.honoka.common.APIUtil;
import com.honoka.entity.BaiduJson.BaiduJsonDirectionDriving;
import com.honoka.entity.BaiduJson.BaiduJsonDirectionTransit;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;

@Service
public class BaiduAPIServiceImpl implements BaiduAPIService {

    @Qualifier("APIKeyServiceImpl")
    @Resource
    private APIKeyService apiKeyService;

    @Override
    public BaiduJsonGeocoding BaiduGeoCoding(String reqAddr) throws Exception {
        String json;
        // 拼接请求字符串获得内容
        json = APIUtil.readUrl("http://api.map.baidu.com/geocoder/v2/?address=" + URLEncoder.encode(reqAddr, "UTF-8")
                + "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        //System.out.println(json);
        Gson gson = new Gson();
        // Json to POJO
        return gson.fromJson(json, BaiduJsonGeocoding.class);
    }

    @Override
    public BaiduJsonPlace BaiduPlaceLocal(String reqKeyWord, Integer pageSize, Integer pageNum, String reqRegion) throws Exception {
        String json;
        json = APIUtil.readUrl(
                "http://api.map.baidu.com/place/v2/search?ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU")
                        + "&output=json&query=" + URLEncoder.encode(reqKeyWord, "UTF-8") + "&page_size=" + pageSize + "&page_num="
                        + pageNum + "&scope=1&region=" + URLEncoder.encode(reqRegion, "UTF-8"));
        //System.out.println(json);
        Gson gson = new Gson();
        return gson.fromJson(json, BaiduJsonPlace.class);
    }

    @Override
    public BaiduJsonDirectionDriving BaiduDirectionDriving(String oLat, String oLng, String dLat, String dLng, String origin_region, String destination_region) throws Exception {
        String json;
        json = APIUtil.readUrl(
                "http://api.map.baidu.com/direction/v1?mode=driving&origin=" + oLat + "," + oLng + "&destination=" + dLat + "," + dLng + "&origin_region=" + origin_region + "&destination_region="
                        + destination_region + "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        //System.out.println(json);
        Gson gson = new Gson();
        return gson.fromJson(json, BaiduJsonDirectionDriving.class);
    }

    @Override
    public BaiduJsonDirectionTransit BaiduDirectionTransit(String oLat, String oLng, String dLat, String dLng, String origin_region, String destination_region) throws Exception {
        String json;
        json = APIUtil.readUrl(
                "http://api.map.baidu.com/direction/v1?mode=transit&origin=" + oLat + "," + oLng + "&destination=" + dLat + "," + dLng + "&origin_region=" + origin_region + "&destination_region="
                        + destination_region + "&output=json&ak=" + apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        //System.out.println(json);
        Gson gson = new Gson();
        return gson.fromJson(json, BaiduJsonDirectionTransit.class);
    }
}
