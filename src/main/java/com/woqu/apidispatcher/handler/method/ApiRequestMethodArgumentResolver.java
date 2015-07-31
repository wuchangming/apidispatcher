package com.woqu.apidispatcher.handler.method;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woqu.apidispatcher.api.requestheader.Req_header;
import com.woqu.apidispatcher.handler.annotation.ReqBodyJson;

public class ApiRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> paramType = parameter.getParameterType();

		if (Req_header.class.isAssignableFrom(paramType)) {
			return true;

		} else
			if (JSON.class.isAssignableFrom(paramType) && parameter.getParameterAnnotation(ReqBodyJson.class) != null) {
			return true;

		} else {

			return false;
		}
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		Class<?> paramType = parameter.getParameterType();
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		try {
			String reqStr = IOUtils.toString(request.getInputStream());
			JSONObject reqJson = JSON.parseObject(reqStr);
			JSONObject header_json = reqJson.getJSONObject("Req_Header");
			Req_header reqHeader = JSON.toJavaObject(header_json, Req_header.class);

			if (Req_header.class.isAssignableFrom(paramType)) {
				return reqHeader;
			} else if (JSON.class.isAssignableFrom(paramType)) {
				JSON reqBodyJson = reqJson.getJSONObject("Req_Body");
				return reqBodyJson;
			} else {
				// should never happen..
				Method method = parameter.getMethod();
				throw new UnsupportedOperationException(
						"Unknown parameter type: " + paramType + " in method: " + method);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
