package com.baidu.push.http;

import java.util.HashMap;
import java.util.Map;

/**
 * httpResponse的实体
 * @author liyazhou@baidu.com
 *
 */
public class PushHttpResponse {
	private PushHttpRequest request;
	private String statusText;
	private int statusCode;
	private String content;
	private Map<String, String> headers = new HashMap<String, String>();

	public void addHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getContent() {
		return this.content;
	}

	public Map<String, String> getHeaders() {
		return this.headers;
	}

	public PushHttpRequest getRequest() {
		return this.request;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public String getStatusText() {
		return this.statusText;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRequest(PushHttpRequest request) {
		this.request = request;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

}
