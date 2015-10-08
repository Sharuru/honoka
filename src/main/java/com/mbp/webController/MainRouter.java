package com.mbp.webController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainRouter {
	@RequestMapping(method = RequestMethod.GET)
	public String indexRouter(ModelMap model) {
        System.out.println("In Index");
		model.addAttribute("message", "Hello world!");
		return "main";
	}
}