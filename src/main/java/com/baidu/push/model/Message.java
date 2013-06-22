package com.baidu.push.model;

/**
 * 消息实体
 * @author liyazhou@baidu.com
 *
 */
public class Message {
	/**
	 * 通道标识
	 */
	private String channelId;
	/**
	 * 系统返回的消息id
	 */
	private String messageId;
	/**
	 * 消息标识，用于覆盖消息内容
	 */
	private String messageKey;
	/**
	 * 消息内容
	 */
	private String message;
	/**
	 * 系统返回的消息长度
	 */
	private int messageLength;
	/**
	 * 消息类型
	 */
	private MessageType messageType;
	
	/**
	 * 消息过期时间
	 */
	private int messageExpires;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public int getMessageExpires() {
		return messageExpires;
	}

	public void setMessageExpires(int messageExpires) {
		this.messageExpires = messageExpires;
	}

	@Override
	public String toString() {
		return "Message [channelId=" + channelId + ", messageId=" + messageId
				+ ", messageKey=" + messageKey + ", message=" + message
				+ ", messageLength=" + messageLength + ", messageType="
				+ messageType + ", messageExpires=" + messageExpires + "]";
	}
}
