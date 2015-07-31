package com.woqu.apidispatcher.handler;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woqu.apidispatcher.api.requestheader.Req_header;
/**
 * 匹配api请求条件的 RequestCondition
 * @author HP
 *
 */
public class ApiRequestCondition extends AbstractRequestCondition<ApiRequestCondition> {
	
	private final Set<String> cmds;
	
	public ApiRequestCondition(String... cmds) {
		this(Arrays.asList(cmds));
	}
	
	private ApiRequestCondition(Collection<String> cmds) {
		this.cmds = Collections.unmodifiableSet(new HashSet<String>(cmds));
	}

	/**
	 * @ApiCmdMapping.java 注解值处理方法级的 所以 用不到combine
	 */
	public ApiRequestCondition combine(ApiRequestCondition other) {
		this.cmds.addAll(other.cmds);
		return new ApiRequestCondition(cmds);
	}
	
	/**
	 * 获取匹配条件，如果return null 时表明不匹配
	 */
	public ApiRequestCondition getMatchingCondition(HttpServletRequest request) {
		Req_header reqHeader = null;
		String reqStr = null;
		// 转成Req_header，统一验证、处理等
		try {
			reqStr = IOUtils.toString(request.getInputStream());
			JSONObject reqJson = JSON.parseObject(reqStr);
			JSONObject header_json = reqJson.getJSONObject("Req_Header");	
			reqHeader = JSON.toJavaObject(header_json, Req_header.class);
			String cmd = reqHeader.getCmd();
			if(cmd != null && !"".equals(cmd)){
				if(cmds.contains(cmd)){
					return this;
				}else {
					return null;
				}
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 比较哪个条件为最优匹配(由于只有cmd一个条件所以不允许重复注解相同的cmd)
	 */
	public int compareTo(ApiRequestCondition other, HttpServletRequest request) {
		return  0;
	}

	@Override
	protected Collection<?> getContent() {
		return cmds;
	}

	@Override
	protected String getToStringInfix() {
		return " || ";
	}

}
