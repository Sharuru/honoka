/**
 * 测试用 Controller
 */
package com.honoka.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.honoka.service.TestExampleService;

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
