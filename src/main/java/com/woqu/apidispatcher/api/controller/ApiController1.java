package com.woqu.apidispatcher.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.woqu.apidispatcher.api.requestheader.Req_header;
import com.woqu.apidispatcher.handler.annotation.ApiCmdMapping;
import com.woqu.apidispatcher.handler.annotation.ReqBodyJson;

@Controller
public class ApiController1 {
	
	@ApiCmdMapping("cmd")
	public @ResponseBody JSON mobile(Req_header req_header, @ReqBodyJson JSON reqBodyJson, HttpServletRequest request) {
		System.out.println(reqBodyJson);
		return reqBodyJson;
	}
	
	@ApiCmdMapping("cmd2")
	public @ResponseBody String mobile2(){
		return "api response2";
	}
	
}
