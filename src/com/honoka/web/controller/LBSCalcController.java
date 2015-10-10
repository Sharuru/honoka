package com.honoka.web.controller;

import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.entity.AmapJson.AmapJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.service.APIKeyService;
import com.honoka.service.AmapAPIService;
import com.honoka.service.BaiduAPIService;

@Controller
public class LBSCalcController {

	@Resource
	private APIKeyService apiKeyService;
	@Resource
	private BaiduAPIService baiduAPIService;
	@Resource
	private AmapAPIService amapAPIService;
	private BaiduJsonGeocoding bdReqResult;
	private AmapJsonGeocoding apReqResult;

	// 地址解析初始画面
	@RequestMapping(value = "/geoCoding", method = RequestMethod.GET)
	public String geoCodingRouter(ModelMap model) {
		System.out.println("In Geo Coding");
		model.addAttribute("bdReqStatus", "info");
		model.addAttribute("apReqStatus", "info");
		model.addAttribute("bdGeocodingResult", "百度通道等待用户输入……");
		model.addAttribute("apGeocodingResult", "高德通道等待用户输入……");
		return "lbsCalc/geoCoding";
	}

	// 请求地址解析
	@RequestMapping(value = "/reqGeoCoding", method = RequestMethod.POST)
	public String reqGeoCodingRouter(ModelMap model, String reqAddr) {
		System.out.println("In REQ Geo Coding");
		System.out.println("Get: reqAddr is: " + reqAddr);
		// 子线程并发发起 API 请求
		Vector<Thread> threads = new Vector<Thread>();
		Thread reqBdThread = new Thread(new Runnable() {
			public void run() {
				try {
					bdReqResult = baiduAPIService.BaiduGeoCoding(reqAddr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		threads.add(reqBdThread);
		reqBdThread.start();
		Thread reqApThread = new Thread(new Runnable() {
			public void run() {
				try {
					apReqResult = amapAPIService.AmapGeoCoding(reqAddr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		threads.add(reqApThread);
		reqApThread.start();
		for (Thread checkThread : threads) {
			try {
				// 所有线程执行完毕
				checkThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("All sub-thread finished.");
		// TODO: Error Handler
		// 解析状态处理
		if (bdReqResult.getStatus() == 0) {
			// 百度通道正常解析
			model.addAttribute("bdGeocodingResult",
					"百度解析结果：" + bdReqResult.getResult().getLocation().getLng().toString() + ","
							+ bdReqResult.getResult().getLocation().getLat().toString());
			model.addAttribute("bdReqStatus", "success");
		} else {
			model.addAttribute("bdGeocodingResult", bdReqResult.getMsg());
			model.addAttribute("bdReqStatus", "danger");
		}
		if (apReqResult.getStatus() == 1 && apReqResult.getCount() >= 1) {
			// 高德通道正常解析且有记录
			// TODO： 高德的 API 只要你连上去了都丢给你状态码 1，要进行判断不仅要靠 info 还要靠 count
			model.addAttribute("apGeocodingResult", "高德解析结果：" + apReqResult.getGeocodes()[0].getLocation());
			model.addAttribute("apReqStatus", "success");
		} else {
			model.addAttribute("apGeocodingResult", "在解析时发生异常");
			// model.addAttribute("apGeocodingResult", apReqResult.getInfo());
			model.addAttribute("apReqStatus", "danger");
		}
		model.addAttribute("userInput", reqAddr);
		return "lbsCalc/geoCoding";
	}

}
