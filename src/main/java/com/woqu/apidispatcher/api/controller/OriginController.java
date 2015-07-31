package com.woqu.apidispatcher.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OriginController {
	
	@RequestMapping("/")
	public @ResponseBody String index() {
		return "indelx";
	}
	
	@RequestMapping("msg")
	public ModelAndView message(){
		ModelAndView view = new ModelAndView("showMessage");
		view.addObject("message", "mapping message()");
		return view;
	}
	
	@RequestMapping("mobile")
	public @ResponseBody String mobile() {
		return "mapping origin Mobile【原始】";
	}

}
