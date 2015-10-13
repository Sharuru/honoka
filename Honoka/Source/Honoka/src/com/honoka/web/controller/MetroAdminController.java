package com.honoka.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.entity.BaiduJson.BaiduJsonPlace;
import com.honoka.entity.BaiduJson.BaiduPlaceResults;
import com.honoka.entity.Metro;
import com.honoka.service.BaiduAPIService;
import com.honoka.service.MetroAdminService;

@Controller
public class MetroAdminController {

	@Resource
	private MetroAdminService metroAdminService;
	@Resource
	private BaiduAPIService baiduAPIService;
	
	// 地铁站点数据管理画面
	@RequestMapping(value = "/metroAdmin&reqPage={reqPage}", method = RequestMethod.GET)
	public String metroAdminRouter(ModelMap model, @PathVariable Integer reqPage) {
		System.out.println("In Metro admin");
		System.out.println("currPage = " + reqPage);
		// TODO：这里似乎有性能问题
		model.addAttribute("currPage", reqPage);
		model.addAttribute("totalCount", metroAdminService.countMetroInfo());
		List<Metro> metroList = metroAdminService.selectMetroInfoByPage(reqPage);
		model.addAttribute("metroInfoList", metroList);
		return "metroAdmin/metroMain";
	}

	// 更新地铁站点信息数据
	@RequestMapping(value = "/reqRefreshMetroInfo")
	public String mainRouter() {
		System.out.println("In req Refresh Metro Info");
		try {
			BaiduJsonPlace bdReqResult = baiduAPIService.BaiduPlace("地铁站", 0, "上海市");
			//TODO：这里的结果处理要优化
			//如果获得解析结果
			if(bdReqResult.getStatus() == 0){
				//先清空所有已有数据
				metroAdminService.initMetroInfo();
				//循环发起请求获取结果
				for(int i=0;i<bdReqResult.getTotal()/20;i++){
					bdReqResult = baiduAPIService.BaiduPlace("地铁站", i, "上海市");
					//写入数据库
					for(int j=0;j<bdReqResult.getResults().size();j++){
						String[] lineNameSplit = bdReqResult.getResults().get(j).getAddress().split(";");
						//不同线路单独写入
						for(int k=0;k<lineNameSplit.length;k++){
							if(!("null".equals(lineNameSplit[k]))){
								//线路名不为空时写入（比如松江站，松江新城站）
								metroAdminService.insertMetroInfo(lineNameSplit[k], "HOLD", bdReqResult.getResults().get(j).getName());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return "metroAdmin/metroMain";
	}
}
