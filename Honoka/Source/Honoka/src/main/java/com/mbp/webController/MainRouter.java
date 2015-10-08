package com.mbp.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class MainRouter {
    //首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexRouter(ModelMap model) {
        System.out.println("In Index");
        model.addAttribute("message", "Hello world!");
        return "main";
    }

    //仪表盘
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardRouter(ModelMap model) {
        System.out.println("In Dashboard");
        return "dashboard/dashMain";
    }


}