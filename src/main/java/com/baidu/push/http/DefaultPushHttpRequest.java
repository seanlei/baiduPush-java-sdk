package com.baidu.push.http;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.baidu.push.reqeust.BaiduPushRequest;

/**
 * http访问方法的载体
 * @author liyazhou@baidu.com
 *
 */
public class DefaultPushHttpRequest implements PushHttpRequest{
	private Map<String, String> parameters = new TreeMap<String, String>();
	private Map<String, String> headers = new HashMap<String, String>();
	
	private final BaiduPushRequest originalRequest;
	private HttpMethodName httpMethod;

	public DefaultPushHttpRequest() {
		this(null);
	}

	public DefaultPushHttpRequest(BaiduPushRequest originalRequest) {
		this.originalRequest = originalRequest;
	}

	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public void addParameter(String name, String value) {
		this.parameters.put(name, value);
	}
	
	@Override
	public void addParameter(String name, Object value) {
		this.parameters.put(name, value.toString());
	}
	
	public Map<String, String> getHeaders() {
		return this.headers;
	}

	public HttpMethodName getHttpMethod() {
		return this.httpMethod;
	}

	public BaiduPushRequest getOriginalRequest() {
		return this.originalRequest;
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}
	
	public void setHttpMethod(HttpMethodName httpMethod) {
		this.httpMethod = httpMethod;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.getHttpMethod().toString() + " ");

		if (!this.getParameters().isEmpty()) {
			builder.append("Parameters: (");
			for (String key : this.getParameters().keySet()) {
				String value = this.getParameters().get(key);
				builder.append(key + ": " + value + ", ");
			}
			builder.append(") ");
		}

		if (!this.getHeaders().isEmpty()) {
			builder.append("Headers: (");
			for (String key : this.getHeaders().keySet()) {
				String value = this.getHeaders().get(key);
				builder.append(key + ": " + value + ", ");
			}
			builder.append(") ");
		}

		return builder.toString();
	}
}
