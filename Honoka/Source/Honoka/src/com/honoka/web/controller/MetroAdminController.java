package com.honoka.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honoka.entity.BaiduJson.PlaceResults;
import com.honoka.entity.Metro;
import com.honoka.entity.Staff;
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
			List<PlaceResults> reLi = baiduAPIService.BaiduPlace("地铁站");
			for(int i =0;i<reLi.size();i++){
				System.out.println(reLi.get(i).getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "metroAdmin/metroMain";
	}
}
