package com.woqu.apidispatcher.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woqu.apidispatcher.handler.annotation.ApiCmdMapping;

@Controller
public class ApiController2 {
	
	@ApiCmdMapping({"cmd4", "cmd41"})
	public @ResponseBody String mobile() {
		return "cmd4 WOQUWAP001";
	}
	
	@ApiCmdMapping(value = "cmd5")
	public @ResponseBody String mobile2(){
		return "cmd5";
	}
	
}
