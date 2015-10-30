/**
 * 地铁站点数据管理 Controller
 */
package com.honoka.web.controller;

import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.entity.Metro;
import com.honoka.service.BaiduAPIService;
import com.honoka.service.MetroAdminService;
import com.honoka.service.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MetroAdminController {

    @Resource
    private MetroAdminService metroAdminService;
    @Resource
    private BaiduAPIService baiduAPIService;
    @Resource
    private PointService pointService;

    // 地铁站点数据管理画面
    @RequestMapping(value = "/metroAdmin&reqPage={reqPage}", method = RequestMethod.GET)
    public String metroAdminRouter(ModelMap model, @PathVariable Integer reqPage) {
        System.out.println("In /metroAdmin&reqPage=" + reqPage);
        // 参数设置
        Map<String, Object> pageParaMap = new HashMap<>();
        System.out.println("currPage = " + reqPage);
        pageParaMap.put("currPage", reqPage);
        pageParaMap.put("totalCount", metroAdminService.countMetroInfo());
        List<Metro> metroList = metroAdminService.selectMetroInfoByPage(reqPage);
        pageParaMap.put("metroInfoList", metroList);
        model.addAttribute("pageParaMap", pageParaMap);
        return "metroAdmin/metroMain";
    }

    // 更新地铁站点信息数据
    @RequestMapping(value = "/reqRefreshMetroInfo")
    public String mainRouter() {
        System.out.println("In /reqRefreshMetroInfo");
        try {
            BaiduJsonPlace bdReqResult = baiduAPIService.BaiduPlaceLocal("地铁站", 20, 0, "上海市");
            // 如果获得解析结果
            if (bdReqResult.getStatus() == 0) {
                // 先清空所有已有数据
                metroAdminService.initMetroInfo();
                pointService.initMetroPoint();
                Integer staIdCount = 0;
                // 循环发起请求获取结果，20 为 pageNum
                for (int i = 0; i < bdReqResult.getTotal() / 20; i++) {
                    bdReqResult = baiduAPIService.BaiduPlaceLocal("地铁站", 20, i, "上海市");
                    // 写入数据库
                    for (int j = 0; j < bdReqResult.getResults().size(); j++) {
                        String[] lineNameSplit = bdReqResult.getResults().get(j).getAddress().split(";");
                        // 不同线路单独写入
                        for (String currLineName : lineNameSplit) {
                            Pattern p = Pattern.compile("地铁(.*)号线.*");
                            Matcher m = p.matcher(currLineName);
                            // 线路名不为空时写入（比如松江站，松江新城站），线路名不为奇怪数据时写入
                            if (m.matches()) {
                                metroAdminService.insertMetroInfo(currLineName, "STA" + staIdCount.toString(),
                                        bdReqResult.getResults().get(j).getName());
                                // 写入坐标信息
                                pointService.insertPointInfo("STA" + staIdCount.toString(),
                                        bdReqResult.getResults().get(j).getLocation().getLng(),
                                        bdReqResult.getResults().get(j).getLocation().getLat(), 0.0, 0.0, "STATION");
                                staIdCount++;
                            }
                        }
                    }
                }
                // Trim
                pointService.trimMetroPointData();
            }
        } catch (Exception e) {
            System.out.println("Error happened when updating metro information.");
        }
        return "redirect:metroAdmin&reqPage=1";
    }
}
