package com.baidu.push.response;

/**
 * @author liyazhou@baidu.com
 *
 */
public class PushResponse<T> {
	private T result;
	/**
	 * 该字段的值由Webserver生成，返回给用户方便问题追查与定位。
	 */
	private String requestId;

	public String getRequestId() {
		return this.requestId;
	}

	public T getResult() {
		return this.result;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PushResponse [result=" + result + ", requestId=" + requestId
				+ "]";
	}
}
