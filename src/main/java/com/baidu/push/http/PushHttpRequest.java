package com.baidu.push.http;

import java.util.Map;

import com.baidu.push.reqeust.BaiduPushRequest;

/**
 * httpRequest的实体
 * @author liyazhou@baidu.com
 *
 */
public interface PushHttpRequest {
	 void addHeader(String name, String value);
	 
	 Map<String, String> getHeaders();

	 void addParameter(String name, String value);
	 
	 void addParameter(String name, Object value);
	 
	 Map<String, String> getParameters();
	 
	 HttpMethodName getHttpMethod();
	 
	 void setHttpMethod(HttpMethodName httpMethod);

	 BaiduPushRequest getOriginalRequest();
}
