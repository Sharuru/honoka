package com.honoka.web.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.honoka.entity.AmapJson.AmapJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.Metro;
import com.honoka.entity.POINT;
import com.honoka.service.APIKeyService;
import com.honoka.service.AmapAPIService;
import com.honoka.service.BaiduAPIService;
import com.honoka.service.MetroAdminService;
import com.honoka.service.PointService;

@Controller
public class LBSCalcController {

	@Resource
	private APIKeyService apiKeyService;
	@Resource
	private BaiduAPIService baiduAPIService;
	@Resource
	private AmapAPIService amapAPIService;
	@Resource
	private PointService pointService;
	@Resource
	private MetroAdminService metroAdminService;
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
		// 新建并发线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(new Runnable() {
			// 百度 API 调用
			public void run() {
				try {
					bdReqResult = baiduAPIService.BaiduGeoCoding(reqAddr);
				} catch (Exception e) {
					System.out.println("Error happened when calling baiduAPI");
				}
			}
		});
		threadPool.execute(new Runnable() {
			// 高德 API 调用
			public void run() {
				try {
					apReqResult = amapAPIService.AmapGeoCoding(reqAddr);
				} catch (Exception e) {
					System.out.println("Error happened when calling amapAPI");
				}
			}
		});
		threadPool.shutdown();
		try {
			// 超时时间为 5 秒
			threadPool.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All sub-thread finished.");
		// 解析状态及超时处理
		try {
			if (bdReqResult.getStatus() == 0) {
				// 百度通道正常解析
				model.addAttribute("bdGeocodingResult",
						"百度解析结果：" + bdReqResult.getResult().getLocation().getLng().toString() + ","
								+ bdReqResult.getResult().getLocation().getLat().toString());
				model.addAttribute("bdReqStatus", "success");
			} else {
				model.addAttribute("bdGeocodingResult", "百度解析结果：" + bdReqResult.getMsg());
				model.addAttribute("bdReqStatus", "danger");
			}
		} catch (Exception e) {
			model.addAttribute("bdGeocodingResult", "百度解析结果：解析超时");
			model.addAttribute("bdReqStatus", "danger");
		}
		try {
			if (apReqResult.getStatus() == 1 && apReqResult.getCount() >= 1) {
				// 高德通道正常解析且有记录
				// TODO：高德的 API 只要你连上去了都丢给你状态码 1，要进行判断不仅要靠 info 还要靠 count
				model.addAttribute("apGeocodingResult", "高德解析结果：" + apReqResult.getGeocodes()[0].getLocation());
				model.addAttribute("apReqStatus", "success");
			} else {
				model.addAttribute("apGeocodingResult", "高德解析结果：在解析时发生异常");
				model.addAttribute("apReqStatus", "danger");
			}
		} catch (Exception e) {
			model.addAttribute("apGeocodingResult", "高德解析结果：解析超时");
			model.addAttribute("apReqStatus", "danger");
		}
		model.addAttribute("userInput", reqAddr);
		return "lbsCalc/geoCoding";
	}

	// 两点计算初始画面
	@RequestMapping(value = "/twoPointCalc", method = RequestMethod.GET)
	public String twoPointRouter(ModelMap model) {
		System.out.println("In two point");
		model.addAttribute("bdAPIKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		model.addAttribute("calcResult", "等待点选");
		return "lbsCalc/twoPointCalc";
	}

	@RequestMapping(value = "/reqTwoPointCalc", method = RequestMethod.POST)
	public void reqTwoPointRouter(ModelMap model, String destPointLng, String destPointLat,
			HttpServletResponse response) throws Exception {
		System.out.println("In req two poing calc");
		System.out.println(destPointLng + " , " + destPointLat);
		// 计算目标点和库中所有员工数据的直线距离
		List<POINT> resPointList = pointService.selectAllStaffPointInfo();
		// 循环计算距离
		Double totalDist = 0.0;
		for (int i = 0; i < resPointList.size(); i++) {
			totalDist += getDistance(Double.parseDouble(destPointLng), Double.parseDouble(destPointLat),
					resPointList.get(i).getBaiduRecordLng(), resPointList.get(i).getBaiduRecordLat());
		}
		Double avgDist = totalDist / resPointList.size();
		System.out.println("AVG IS " + Double.toString(avgDist));
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(avgDist));
	}

	// 地理围栏初始画面
	@RequestMapping(value = "/geoFencing", method = RequestMethod.GET)
	public String geoFencingRouter(ModelMap model) {
		System.out.println("In geo fencing");
		return "lbsCalc/geoFencing";
	}

	// 地理围栏计算
	@RequestMapping(value = "/reqCalcGeoFencing", method = RequestMethod.POST)
	public String reqCalcGeoFencing(ModelMap model, String reqRange) {
		System.out.println("In req calc geo fencing");
		System.out.println("Get reqRange = " + reqRange);
		// TODO：数据库表要重新设计，线路 ID
		// 计算准备
		// 获取员工坐标点信息
		List<POINT> staffPointList = pointService.selectAllStaffPointInfo();
		// 获取系统中所有已有线路列表
		List<Metro> metroLineNameList = metroAdminService.getMetroLineNameList();
		// 获取线路对应的站点 ID
		for (int i = 0; i < metroLineNameList.size(); i++) {
			// System.out.println("Getting station id on: " +
			// metroLineNameList.get(i).getLineName());
			List<Metro> metroStationIdList = metroAdminService
					.getMetroStationIdByLineName(metroLineNameList.get(i).getLineName());
			// 对应站点 ID 获取 POINT 信息
			for (int j = 0; j < metroStationIdList.size(); j++) {
				// System.out.println("Getting station point for: " +
				// metroStationIdList.get(j).getStaId());
				List<POINT> stationPointList = pointService
						.selectPointInfoByKeyId(metroStationIdList.get(j).getStaId());
				// 开始计算比对
				for (int k = 0; k < staffPointList.size(); k++) {
					for (int l = 0; l < stationPointList.size(); l++) {
						Double dist = getDistance(staffPointList.get(k).getBaiduRecordLng(),
								staffPointList.get(k).getBaiduRecordLat(), stationPointList.get(l).getBaiduRecordLng(),
								stationPointList.get(l).getBaiduRecordLat());
						// System.out.println("Curr dist is " + dist);
						if (dist < Double.parseDouble(reqRange)) {
							// 在范围内插入显示列表
							System.out.println(staffPointList.get(k).getKeyId() + metroLineNameList.get(i).getLineName()
									+ stationPointList.get(l).getKeyId());
						}
					}

				}
			}
		}
		System.out.println("Calculate finished");
		return "lbsCalc/geoFencing";
		// return null;
	}

	public double getDistance(double lng1, double lat1, double lng2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (lng1 - lng2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public static class fencingResult {
		private String staffId;
		private String staffName;
		private String lineName;
		private String staName;
		private Double dist;

		public String getStaffId() {
			return staffId;
		}

		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}

		public String getStaffName() {
			return staffName;
		}

		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}

		public String getLineName() {
			return lineName;
		}

		public void setLineName(String lineName) {
			this.lineName = lineName;
		}

		public String getStaName() {
			return staName;
		}

		public void setStaName(String staName) {
			this.staName = staName;
		}

		public Double getDist() {
			return dist;
		}

		public void setDist(Double dist) {
			this.dist = dist;
		}

	}
}
