package com.woqu.apidispatcher.handler.method;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.woqu.apidispatcher.api.requestheader.Req_header;
import com.woqu.apidispatcher.handler.annotation.ReqBodyJson;

/**
 * 解析Handler参数：
 * 1.解析好的Req_header对象。遇到类型为Req_header的参数，直接传入。
 * 2.解析好的reqBodyJson对象。遇到类型为JSON同时有@ReqBodyJson注解的参数，直接传入该值。
 * 
 * @author HP
 *
 */
public class ApiRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	public static final String REQ_HEADER_CLASS_ATTRIBUTE = "woqu_api_req_header_class";
	public static final String REQ_BODY_JSON_ATTRIBUTE = "woqu_api_req_body_json";
	
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

		if (Req_header.class.isAssignableFrom(paramType)) {
			return request.getAttribute(REQ_HEADER_CLASS_ATTRIBUTE);
		} else if (JSON.class.isAssignableFrom(paramType) && parameter.getParameterAnnotation(ReqBodyJson.class) != null) {
			return request.getAttribute(REQ_BODY_JSON_ATTRIBUTE);
		} else {
			// should never happen..
			Method method = parameter.getMethod();
			throw new UnsupportedOperationException(
					"Unknown parameter type: " + paramType + " in method: " + method);
		}

	}

}
