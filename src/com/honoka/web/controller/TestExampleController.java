/**
 * 测试用 Controller
 */
package com.honoka.web.controller;

import com.honoka.service.TestExampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TestExampleController {
    @Resource
    private TestExampleService testExampleService;

    @RequestMapping(value = "/Test")
    public String mainRouter() {
        System.out.println("In /Test");
        return "main";
    }
}
