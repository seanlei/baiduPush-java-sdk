package com.baidu.push.util;

/**
 * 服务器端异常
 * @author liyazhou@baidu.com
 *
 */
public class PushServiceException extends PushClientException {
	private static final long serialVersionUID = -4699145960925002524L;
	
	private String requestId;
	private int pushErrorCode;
	private String pushErrorMessage;

	public PushServiceException(String message) {
		super(message);
	}

	public PushServiceException(String message, Throwable t) {
		super(message, t);
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getPushErrorCode() {
		return pushErrorCode;
	}

	public void setPushErrorCode(int pushErrorCode) {
		this.pushErrorCode = pushErrorCode;
	}

	public String getPushErrorMessage() {
		return pushErrorMessage;
	}

	public void setPushErrorMessage(String pushErrorMessage) {
		this.pushErrorMessage = pushErrorMessage;
	}
}