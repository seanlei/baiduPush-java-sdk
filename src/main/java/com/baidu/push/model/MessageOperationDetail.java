package com.baidu.push.model;

/**
 * 删除消息后的操作状态实体
 * @author liyazhou@baidu.com
 *
 */
public class MessageOperationDetail {
	/**
	 * 消息标识
	 */
	private String messageId;
	/**
	 * 操作结果标识
	 */
	private OperationStatus status;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public OperationStatus getStatus() {
		return status;
	}
	public void setStatus(OperationStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MessageOperationDetail [messageId=" + messageId + ", status="
				+ status + "]";
	}
}
