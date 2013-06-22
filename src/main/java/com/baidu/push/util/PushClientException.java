package com.baidu.push.util;

/**
 * 客户端异常
 * @author liyazhou@baidu.com
 *
 */
public class PushClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PushClientException(String message) {
		super(message);
	}

	public PushClientException(String message, Throwable t) {
		super(message, t);
	}
}