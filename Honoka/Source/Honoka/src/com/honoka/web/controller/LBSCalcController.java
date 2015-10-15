package com.honoka.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		System.out.println("In /geoCoding");
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		pageParaMap.put("reqAddr", "");
		pageParaMap.put("bdReqStatus", "info");
		pageParaMap.put("apReqStatus", "info");
		pageParaMap.put("bdGeocodingResult", "百度通道等待用户输入……");
		pageParaMap.put("apGeocodingResult", "高德通道等待用户输入……");
		model.addAttribute("pageParaMap", pageParaMap);
		return "lbsCalc/geoCoding";
	}

	// 请求地址解析
	@RequestMapping(value = "/reqGeoCoding", method = RequestMethod.POST)
	public String reqGeoCodingRouter(ModelMap model, String reqAddr) {
		System.out.println("In /reqGeoCoding");
		System.out.println("reqAddr is: " + reqAddr);
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
			System.out.println("Error happened when awaitTermination");
		}
		System.out.println("All sub-thread finished, starting main-thread");
		// 解析状态及超时处理
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		// 百度通道
		if (bdReqResult != null) {
			if (bdReqResult.getStatus() == 0) {
				pageParaMap.put("bdGeocodingResult",
						"百度解析结果：" + bdReqResult.getResult().getLocation().getLng().toString() + ","
								+ bdReqResult.getResult().getLocation().getLat().toString());
				pageParaMap.put("bdReqStatus", "success");
			} else {
				pageParaMap.put("bdGeocodingResult", "百度解析结果：" + bdReqResult.getMsg());
				pageParaMap.put("bdReqStatus", "danger");
			}
		} else {
			pageParaMap.put("bdGeocodingResult", "百度解析结果：解析超时");
			pageParaMap.put("bdReqStatus", "danger");
		}
		// 高德通道
		// 高德的 API 只要你连上去了都丢给你状态码 1，要进行判断不仅要靠 info 还要靠 count
		// 不对高德的 API 做特殊的异常信息处理
		if (apReqResult != null && apReqResult.getStatus() == 1 && apReqResult.getCount() >= 1) {
			pageParaMap.put("apGeocodingResult", "高德解析结果：" + apReqResult.getGeocodes()[0].getLocation());
			pageParaMap.put("apReqStatus", "success");
		} else {
			pageParaMap.put("apGeocodingResult", "高德解析结果：在解析时发生异常");
			pageParaMap.put("apReqStatus", "danger");
		}
		pageParaMap.put("inputReqAddr", reqAddr);
		model.addAttribute("pageParaMap",pageParaMap);
		return "lbsCalc/geoCoding";
	}

	// 两点计算初始画面
	@RequestMapping(value = "/twoPointCalc", method = RequestMethod.GET)
	public String twoPointRouter(ModelMap model) {
		System.out.println("In /twoPoingCalc");
		// 参数设置
		Map<String, Object> pageParaMap = new HashMap<String, Object>();
		pageParaMap.put("bdAPIKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
		pageParaMap.put("calcResult", "等待点选");
		model.addAttribute("pageParaMap", pageParaMap);
		return "lbsCalc/twoPointCalc";
	}

	// 请求两点计算
	@RequestMapping(value = "/reqTwoPointCalc", method = RequestMethod.POST)
	public void reqTwoPointRouter(ModelMap model, String destPointLng, String destPointLat,
			HttpServletResponse response) throws Exception {
		System.out.println("In /reqTwoPointCalc");
		System.out.println("Get destPoint: " + destPointLng + "," + destPointLat);
		// 计算目标点和库中所有员工数据的直线距离
		List<POINT> resPointList = pointService.selectAllStaffPointInfo();
		// 循环计算距离
		Double totalDist = 0.0;
		for (int i = 0; i < resPointList.size(); i++) {
			totalDist += getDistance(Double.parseDouble(destPointLng), Double.parseDouble(destPointLat),
					resPointList.get(i).getBaiduRecordLng(), resPointList.get(i).getBaiduRecordLat());
		}
		//保留两位小数
		Double avgDist = Math.round(totalDist / resPointList.size() * 100.0) / 100.0;
		System.out.println("avgDist is: " + Double.toString(avgDist));
		//转换单位
		String avgDistStr = "";
		if(avgDist > 1000.0){
			//转换成公里
			avgDistStr = Double.toString(Math.round(avgDist/1000 * 100.0) / 100.0) + " 公里";
		}
		else{
			avgDistStr = Double.toString(avgDist) + " 米";
		}
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(avgDistStr));
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
		// 结果保存
		List<fencingResult> fencingResultList = new ArrayList<fencingResult>();
		// 获取员工坐标点信息
		List<POINT> staffPointList = pointService.selectAllStaffPointInfo();
		// 获取系统中所有已有线路列表
		List<Metro> metroLineNameList = metroAdminService.getMetroLineNameList();
		// 获取线路对应的站点 ID
		for (int i = 0; i < metroLineNameList.size(); i++) {
			System.out.println("Getting station id on: " + metroLineNameList.get(i).getLineName());
			List<Metro> metroStationIdList = metroAdminService
					.getMetroStationIdByLineName(metroLineNameList.get(i).getLineName());
			// 对应站点 ID 获取 POINT 信息
			System.out.println(metroStationIdList.size());
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
							fencingResult fRo = new fencingResult();
							fRo.setStaffId(staffPointList.get(k).getKeyId());
							fRo.setLineName(metroLineNameList.get(i).getLineName());
							fRo.setStaName(metroAdminService
									.getMetroStationNameByStationId(stationPointList.get(l).getKeyId()));
							fencingResultList.add(fRo);
						}
					}
				}
			}
		}
		System.out.println("Calculate finished");
		for (int i = 0; i < fencingResultList.size(); i++) {
			System.out.println(fencingResultList.get(i).getLineName() + fencingResultList.get(i).getStaffId()
					+ fencingResultList.get(i).getStaName());
		}
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
