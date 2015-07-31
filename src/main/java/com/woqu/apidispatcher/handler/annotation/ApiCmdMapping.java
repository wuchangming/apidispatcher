package com.woqu.apidispatcher.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  		！！！！！！！！这里有HardCode！！！！！！
 * 		注：这里默认带上了 @RequestMapping("/mobile")！！！
 * 
 * 通过 Api Req_Header 相关字段做分发
 * 参考wiki：http://wiki.woqu.com/doku.php?id=woqu-app-communication-protocol#消息头部_req_header_rsp_header_信息
 * 
 * 
 * @author HP
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping("/mobile")
public @interface ApiCmdMapping {
	
	/**
	 * 对应cmd属性，不能为空
	 * @return
	 */
	String[] value();
	
}
