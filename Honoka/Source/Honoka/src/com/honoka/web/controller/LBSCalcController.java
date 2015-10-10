package com.honoka.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.service.APIKeyService;
import com.honoka.service.BaiduAPIService;
import com.honoka.service.impl.BaiduAPIServiceImpl.BaiduJson;

@Controller
public class LBSCalcController {

	@Resource
	private APIKeyService apiKeyService;
	@Resource
	private BaiduAPIService baiduAPIService;

	// 地址解析初始画面
	@RequestMapping(value = "/geoCoding", method = RequestMethod.GET)
	public String geoCodingRouter(ModelMap model) {
		System.out.println("In Geo Coding");
		model.addAttribute("reqStatus", "info");
		model.addAttribute("bdGeocodingResult", "等待用户输入……");
		return "lbsCalc/geoCoding";
	}

	// 请求地址解析
	@RequestMapping(value = "/reqGeoCoding", method = RequestMethod.POST)
	public String reqGeoCodingRouter(ModelMap model, String reqAddr) {
		// TODO： 多 API 时 result 是一个结果集，或者分别调用设置（控制性好）
		BaiduJson bdReqResult = null;
		System.out.println("In REQ Geo Coding");
		System.out.println("Get: reqAddr is: " + reqAddr);
		try {
			bdReqResult = baiduAPIService.BaiduGeoCoding(reqAddr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 异常处理
		if (bdReqResult.getStatus() == 0) {
			// 正常解析
			model.addAttribute("bdGeocodingResult", "解析结果：" + bdReqResult.getResult().getLocation().getLng().toString() + ", "
					+ bdReqResult.getResult().getLocation().getLat().toString() + " 可信度：" + bdReqResult.getResult().getConfidence().toString());
			model.addAttribute("reqStatus", "success");
		}
		else{
			model.addAttribute("bdGeocodingResult", bdReqResult.getMsg());
			model.addAttribute("reqStatus", "danger");
		}
		model.addAttribute("userInput", reqAddr);
		return "lbsCalc/geoCoding";
	}

}
