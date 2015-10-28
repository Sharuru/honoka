/**
 * LBS 应用 Controller
 */
package com.honoka.web.controller;

import com.google.gson.Gson;
import com.honoka.common.Trimmer;
import com.honoka.entity.AmapJson.AmapJsonGeocoding;
import com.honoka.entity.BaiduJson;
import com.honoka.entity.BaiduJson.BaiduJsonGeocoding;
import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.entity.Metro;
import com.honoka.entity.POINT;
import com.honoka.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Controller
public class LBSApplController {

    @Qualifier("APIKeyServiceImpl")
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
    @Resource
    private StaffAdminService staffAdminService;
    private BaiduJsonGeocoding bdGeoReqResult;
    private AmapJsonGeocoding apReqResult;

    // 地址解析初始画面
    @RequestMapping(value = "/geoCoding", method = RequestMethod.GET)
    public String geoCodingRouter(ModelMap model) {
        System.out.println("In /geoCoding");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        pageParaMap.put("reqAddr", "");
        pageParaMap.put("bdReqStatus", "info");
        pageParaMap.put("apReqStatus", "info");
        pageParaMap.put("bdGeocodingResult", "百度通道等待用户输入……");
        pageParaMap.put("apGeocodingResult", "高德通道等待用户输入……");
        model.addAttribute("pageParaMap", pageParaMap);
        return "lbsAppl/geoCoding";
    }

    // 请求地址解析
    @RequestMapping(value = "/reqGeoCoding", method = RequestMethod.POST)
    public String reqGeoCodingRouter(ModelMap model, String reqAddr) {
        System.out.println("In /reqGeoCoding");
        System.out.println("reqAddr is: " + reqAddr);
        // 新建并发线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> {
            try {
                bdGeoReqResult = baiduAPIService.BaiduGeoCoding(reqAddr);
            } catch (Exception e) {
                System.out.println("Error happened when calling baiduAPI");
            }
        });
        threadPool.execute(() -> {
            try {
                apReqResult = amapAPIService.AmapGeoCoding(reqAddr);
            } catch (Exception e) {
                System.out.println("Error happened when calling amapAPI");
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
        Map<String, Object> pageParaMap = new HashMap<>();
        // 百度通道
        if (bdGeoReqResult != null) {
            if (bdGeoReqResult.getStatus() == 0) {
                pageParaMap.put("bdGeocodingResult",
                        "百度解析结果：" + bdGeoReqResult.getResult().getLocation().getLng().toString() + ","
                                + bdGeoReqResult.getResult().getLocation().getLat().toString());
                pageParaMap.put("bdReqStatus", "success");
            } else {
                pageParaMap.put("bdGeocodingResult", "百度解析结果：" + bdGeoReqResult.getMsg());
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
        model.addAttribute("pageParaMap", pageParaMap);
        return "lbsAppl/geoCoding";
    }

    // 两点计算初始画面
    @RequestMapping(value = "/twoPointCalc", method = RequestMethod.GET)
    public String twoPointRouter(ModelMap model) {
        System.out.println("In /twoPointCalc");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        pageParaMap.put("bdAPIKey", apiKeyService.selectUsableAPIKeyByProvider("BAIDU"));
        pageParaMap.put("calcResult", "等待点选");
        model.addAttribute("pageParaMap", pageParaMap);
        return "lbsAppl/twoPointCalc";
    }

    // POI 检索请求
    @RequestMapping(value = "/reqPOIList&reqPage={reqPage}", method = RequestMethod.POST)
    public String reqPOIListRouter(ModelMap model, @PathVariable Integer reqPage, String reqKeyword) {
        System.out.println("In /reqPOIList&reqPage= " + reqPage);
        // 参数设置
        List<POISearchResult> POISearchResultList = new ArrayList<>();
        Map<String, Object> pageParaMap = new HashMap<>();
        System.out.println("currPage = " + reqPage);
        System.out.println("reqKeyword = " + reqKeyword);
        try {
            BaiduJsonPlace bdPlaceReqResult = baiduAPIService.BaiduPlace(reqKeyword, 5, reqPage - 1, "上海市");
            // 设置总记录条数
            System.out.println("Get total is: " + bdPlaceReqResult.getTotal());
            pageParaMap.put("totalCount", bdPlaceReqResult.getTotal());
            // 获取员工坐标点信息
            List<POINT> staffPointList = pointService.selectAllStaffPointInfo();
            // 基本兴趣点信息计算
            for (int i = 0; i < bdPlaceReqResult.getResults().size(); i++) {
                POISearchResult poiSr = new POISearchResult();
                poiSr.setPoiName(bdPlaceReqResult.getResults().get(i).getName());
                poiSr.setPoiAddr(bdPlaceReqResult.getResults().get(i).getAddress());
                poiSr.setPoiTelephone(bdPlaceReqResult.getResults().get(i).getTelephone());
                poiSr.setBaiduRecordLng(bdPlaceReqResult.getResults().get(i).getLocation().getLng());
                poiSr.setBaiduRecordLat(bdPlaceReqResult.getResults().get(i).getLocation().getLat());
                final Double[] lineDistanceAverage = {0.0};
//                final Double[] drivingDistanceAverage = {0.0};
//                final Integer[] drivingDurationAverage = {0};
//                final Double[] transitDistanceAverage = {0.0};
//                final Integer[] transitDurationAverage = {0};
                // 新建并发线程池
                ExecutorService threadPool = Executors.newFixedThreadPool(500);
                // 计算距离
                for (int j = 0; j < staffPointList.size(); j++) {
                    final int finalJ = j;
                    threadPool.execute(() -> {
                        // 新建子并发线程池
                        ExecutorService subThreadPool = Executors.newCachedThreadPool();
                        subThreadPool.execute(() -> {
                            // 直线距离
                            lineDistanceAverage[0] += getDistance(staffPointList.get(finalJ).getBaiduRecordLng(), staffPointList.get(finalJ).getBaiduRecordLat(), poiSr.getBaiduRecordLng(), poiSr.getBaiduRecordLat());
                        });
//                        subThreadPool.execute(() -> {
//                            // 自驾距离
//                            BaiduJson.BaiduJsonDirectionDriving bdDD = null;
//                            try {
//                                bdDD = baiduAPIService.BaiduDirectionDriving(Double.toString(staffPointList.get(finalJ).getBaiduRecordLat()), Double.toString(staffPointList.get(finalJ).getBaiduRecordLng()), poiSr.getBaiduRecordLat().toString(), poiSr.getBaiduRecordLng().toString(), "上海", "上海");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            if (bdDD.getResult() != null) {
//                                drivingDistanceAverage[0] += bdDD.getResult().getRoutes()[0].getDistance();
//                                drivingDurationAverage[0] += bdDD.getResult().getRoutes()[0].getDuration();
//                            }
//                        });
//                        subThreadPool.execute(() -> {
//                            // 公交距离
//                            BaiduJson.BaiduJsonDirectionTransit bdDT = null;
//                            try {
//                                bdDT = baiduAPIService.BaiduDirectionTransit(Double.toString(staffPointList.get(finalJ).getBaiduRecordLat()), Double.toString(staffPointList.get(finalJ).getBaiduRecordLng()), poiSr.getBaiduRecordLat().toString(), poiSr.getBaiduRecordLng().toString(), "上海", "上海");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            //System.out.println("Transit Distance:" + bdDT.getResult().getRoutes()[0].getScheme()[0].getDistance() + " Transit Duration:" + bdDT.getResult().getRoutes()[0].getScheme()[0].getDuration());
//                            if (bdDT.getResult() != null) {
//                                transitDistanceAverage[0] += bdDT.getResult().getRoutes()[0].getScheme()[0].getDistance();
//                                transitDurationAverage[0] += bdDT.getResult().getRoutes()[0].getScheme()[0].getDuration();
//                            }
//                        });
                        subThreadPool.shutdown();
                        try {
                            // 超时时间为 30 秒
                            subThreadPool.awaitTermination(30, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            System.out.println("Error happened when await sub Termination");
                        }
                        //System.out.println("Driving Distance:" + bdDD.getResult().getRoutes()[0].getDistance() + " Driving Duration:" + bdDD.getResult().getRoutes()[0].getDuration());
                    });
                }
                threadPool.shutdown();
                try {
                    // 超时时间为 30 秒
                    threadPool.awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println("Error happened when awaitTermination");
                }
                System.out.println("All sub-thread finished, starting main-thread");
                poiSr.setLineDistance(Trimmer.distance(lineDistanceAverage[0] / staffPointList.size()));
//                poiSr.setDrivingDistance("等待计算");
//                poiSr.setDrivingDuration("等待计算");
//                poiSr.setTransitDistance("等待计算");
//                poiSr.setTransitDuration("等待计算");
//                poiSr.setLineDistance(Trimmer.distance(lineDistanceAverage[0] / staffPointList.size()));
//                poiSr.setDrivingDistance(Trimmer.distance(drivingDistanceAverage[0] / staffPointList.size()));
//                poiSr.setDrivingDuration(Trimmer.time(drivingDurationAverage[0] / staffPointList.size()));
//                poiSr.setTransitDistance(Trimmer.distance(transitDistanceAverage[0] / staffPointList.size()));
//                poiSr.setTransitDuration(Trimmer.time(transitDurationAverage[0] / staffPointList.size()));
                POISearchResultList.add(poiSr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pageParaMap.put("poiSearchList", POISearchResultList);
        pageParaMap.put("currPage", reqPage);
        model.addAttribute("pageParaMap", pageParaMap);
        return "lbsAppl/poiList";
    }

    // 请求两点计算
    @RequestMapping(value = "/reqTwoPointCalc", method = RequestMethod.POST)
    public void reqTwoPointRouter(String destPointLng, String destPointLat,
                                  HttpServletResponse response) throws Exception {
        System.out.println("In /reqTwoPointCalc");
        System.out.println("Get destPoint: " + destPointLng + "," + destPointLat);
        // 计算目标点和库中所有员工数据的直线距离
        List<POINT> resPointList = pointService.selectAllStaffPointInfo();
        // 循环计算距离
        Double totalDist = 0.0;
        for (POINT currPoint : resPointList) {
            totalDist += getDistance(Double.parseDouble(destPointLng), Double.parseDouble(destPointLat),
                    currPoint.getBaiduRecordLng(), currPoint.getBaiduRecordLat());
        }
        // 保留两位小数
        Double avgDist = Math.round(totalDist / resPointList.size() * 100.0) / 100.0;
        String avgDistStr = Trimmer.distance(avgDist);
        // 返回 Json
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(avgDistStr));
    }

    // 请求行程计算
    @RequestMapping(value = "/reqDirectionCalc", method = RequestMethod.POST)
    public
    @ResponseBody
    String[] reaDirectionRouter(String destPointLng, String destPointLat) {
        System.out.println("In /reqDirectionCalc");
        System.out.println("Get destPoint: " + destPointLng + "," + destPointLat);
        // 获取员工坐标点信息
        List<POINT> staffPointList = pointService.selectAllStaffPointInfo();
        final Double[] drivingDistanceAverage = {0.0};
        final Integer[] drivingDurationAverage = {0};
        final Double[] transitDistanceAverage = {0.0};
        final Integer[] transitDurationAverage = {0};
        // 新建并发线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        // 计算距离
        for (int j = 0; j < staffPointList.size(); j++) {
            final int finalJ = j;
            threadPool.execute(() -> {
                // 新建子并发线程池
                ExecutorService subThreadPool = Executors.newFixedThreadPool(2);
                subThreadPool.execute(() -> {
                    // 自驾距离
                    BaiduJson.BaiduJsonDirectionDriving bdDD = null;
                    try {
                        bdDD = baiduAPIService.BaiduDirectionDriving(Double.toString(staffPointList.get(finalJ).getBaiduRecordLat()), Double.toString(staffPointList.get(finalJ).getBaiduRecordLng()), destPointLat, destPointLng, "上海", "上海");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (bdDD.getResult() != null) {
                        drivingDistanceAverage[0] += bdDD.getResult().getRoutes()[0].getDistance();
                        drivingDurationAverage[0] += bdDD.getResult().getRoutes()[0].getDuration();
                    }
                    //TODO：API 并发量
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                });
                subThreadPool.execute(() -> {
                    // 公交距离
                    BaiduJson.BaiduJsonDirectionTransit bdDT = null;
                    try {
                        bdDT = baiduAPIService.BaiduDirectionTransit(Double.toString(staffPointList.get(finalJ).getBaiduRecordLat()), Double.toString(staffPointList.get(finalJ).getBaiduRecordLng()), destPointLat, destPointLng, "上海", "上海");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.println("Transit Distance:" + bdDT.getResult().getRoutes()[0].getScheme()[0].getDistance() + " Transit Duration:" + bdDT.getResult().getRoutes()[0].getScheme()[0].getDuration());
                    if (bdDT.getResult() != null) {
                        transitDistanceAverage[0] += bdDT.getResult().getRoutes()[0].getScheme()[0].getDistance();
                        transitDurationAverage[0] += bdDT.getResult().getRoutes()[0].getScheme()[0].getDuration();
                    }
                    //TODO：API 并发量
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                });
                subThreadPool.shutdown();
                try {
                    // 超时时间为 30 秒
                    subThreadPool.awaitTermination(30, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    System.out.println("Error happened when await sub Termination");
                }
                //TODO：API 并发量
//                try {
//                    Thread.sleep(550);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        }
        threadPool.shutdown();
        try {
            // 超时时间为 30 秒
            threadPool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Error happened when awaitTermination");
        }
        System.out.println("All sub-thread finished, starting main-thread");
        String[] result = new String[4];
        result[0] = Trimmer.distance(drivingDistanceAverage[0] / staffPointList.size());
        result[1] = Trimmer.time(drivingDurationAverage[0] / staffPointList.size());
        result[2] = Trimmer.distance(transitDistanceAverage[0] / staffPointList.size());
        result[3] = Trimmer.time(transitDurationAverage[0] / staffPointList.size());
        return result;
    }

    // 地理围栏初始画面
    @RequestMapping(value = "/geoFencing", method = RequestMethod.GET)
    public String geoFencingRouter() {
        System.out.println("In geo fencing");
        return "lbsAppl/geoFencing";
    }

    // 地理围栏计算
    @RequestMapping(value = "/reqCalcGeoFencing", method = RequestMethod.POST)
    public String reqCalcGeoFencing(ModelMap model, String reqRange) {
        System.out.println("In req calc geo fencing");
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        System.out.println("Get reqRange = " + reqRange);
        // TODO：数据库表要重新设计，线路 ID
        // 计算准备
        // 结果保存
        List<fencingResult> fencingResultList = new ArrayList<>();
        // 获取员工坐标点信息
        List<POINT> staffPointList = pointService.selectAllStaffPointInfo();
        // 获取系统中所有已有线路列表
        List<Metro> metroLineNameList = metroAdminService.getMetroLineNameList();
        // 获取线路对应的站点 ID
        for (Metro currLineName : metroLineNameList) {
            System.out.println("Getting station id on: " + currLineName.getLineName());
            List<Metro> metroStationIdList = metroAdminService
                    .getMetroStationIdByLineName(currLineName.getLineName());
            // 对应站点 ID 获取 POINT 信息
            int j = 0;
            while (j < metroStationIdList.size()) {
                POINT stationPoint = pointService.selectPointInfoByKeyId(metroStationIdList.get(j).getStaId());
                // 开始计算比对
                for (POINT currPoint : staffPointList) {
                    Double dist = getDistance(currPoint.getBaiduRecordLng(),
                            currPoint.getBaiduRecordLat(), stationPoint.getBaiduRecordLng(),
                            stationPoint.getBaiduRecordLat());
                    if (dist < Double.parseDouble(reqRange)) {
                        // 在范围内插入显示列表
                        fencingResult fRo = new fencingResult();
                        fRo.setStaffId(currPoint.getKeyId());
                        fRo.setStaffName(staffAdminService.selectStaffDetailByStaffId(fRo.getStaffId()).getStaffName());
                        fRo.setLineName(currLineName.getLineName());
                        fRo.setStaName(metroAdminService.getMetroStationNameByStationId(stationPoint.getKeyId()));
                        fRo.setDist(Double.toString(Math.round(dist * 100.0) / 100.0) + " 米");
                        fencingResultList.add(fRo);
                    }
                }
                j++;
            }
        }
        System.out.println("Calculate finished");
        for (fencingResult currFr : fencingResultList) {
            System.out.println(
                    currFr.getLineName() + " || " + currFr.getStaffId() + " || "
                            + currFr.getStaffName() + " || " + currFr.getStaName());
        }
        pageParaMap.put("fencingResultList", fencingResultList);
        model.addAttribute("pageParaMap", pageParaMap);
        return "lbsAppl/geoFencing";
    }

    // 两点距离计算

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

    // TODO：CLEAN
    // 站点围栏 POJO
    public static class fencingResult {
        // 员工工号
        private String staffId;
        // 员工姓名
        private String staffName;
        // 线路名
        private String lineName;
        // 站点名
        private String staName;
        // 距离
        private String dist;

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

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

    }

    // POI 检索 POJO
    public static class POISearchResult {
        // 兴趣点名字
        private String poiName;
        // 兴趣点地址
        private String poiAddr;
        // 兴趣点联系电话
        private String poiTelephone;
        // 兴趣点百度记录纬度
        private float baiduRecordLng;
        // 兴趣点百度记录经度
        private float baiduRecordLat;

        private String lineDistance;
        private String drivingDistance;
        private String drivingDuration;
        private String transitDistance;
        private String transitDuration;

        public String getPoiName() {
            return poiName;
        }

        public void setPoiName(String poiName) {
            this.poiName = poiName;
        }

        public String getPoiAddr() {
            return poiAddr;
        }

        public void setPoiAddr(String poiAddr) {
            this.poiAddr = poiAddr;
        }

        public String getPoiTelephone() {
            return poiTelephone;
        }

        public void setPoiTelephone(String poiTelephone) {
            this.poiTelephone = poiTelephone;
        }

        public Float getBaiduRecordLng() {
            return baiduRecordLng;
        }

        public void setBaiduRecordLng(Float baiduRecordLng) {
            this.baiduRecordLng = baiduRecordLng;
        }

        public Float getBaiduRecordLat() {
            return baiduRecordLat;
        }

        public void setBaiduRecordLat(Float baiduRecordLat) {
            this.baiduRecordLat = baiduRecordLat;
        }

        public String getLineDistance() {
            return lineDistance;
        }

        public void setLineDistance(String lineDistance) {
            this.lineDistance = lineDistance;
        }

        public String getDrivingDistance() {
            return drivingDistance;
        }

        public void setDrivingDistance(String drivingDistance) {
            this.drivingDistance = drivingDistance;
        }

        public String getDrivingDuration() {
            return drivingDuration;
        }

        public void setDrivingDuration(String drivingDuration) {
            this.drivingDuration = drivingDuration;
        }

        public String getTransitDuration() {
            return transitDuration;
        }

        public void setTransitDuration(String transitDuration) {
            this.transitDuration = transitDuration;
        }

        public String getTransitDistance() {
            return transitDistance;
        }

        public void setTransitDistance(String transitDistance) {
            this.transitDistance = transitDistance;
        }
    }
}
