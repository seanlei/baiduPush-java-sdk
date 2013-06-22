package com.baidu.push.reqeust;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.http.HttpMethodName;
import com.baidu.push.model.MessageType;
import com.baidu.push.model.NotifyMessage;
import com.baidu.push.model.PushType;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class PushMessageRequest extends UserRelatedRequest {
	public final static Long DEFAULT_MSG_EXPIRES = 86400l;
	
	private PushType pushType;
	private String tag;
	private MessageType messageType;
	private String messages;
	private String messageKeys;
	private Long messageExpires = DEFAULT_MSG_EXPIRES;
	
	public PushMessageRequest() {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.push_msg);
	}

	public PushType getPushType() {
		return pushType;
	}

	public void setPushType(PushType pushType) {
		this.pushType = pushType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public void setMessages(NotifyMessage notifyMessage){
		this.messages = JSONObject.toJSONString(notifyMessage);
	}
	
	public void setMessages(List<String> messages){
		this.messages = JSONObject.toJSONString(messages);
	}

	public String getMessageKeys() {
		return messageKeys;
	}

	public void setMessageKeys(String messageKeys) {
		this.messageKeys = messageKeys;
	}
	
	public void setMessageKeys(List<String> messageKeys) {
		this.messageKeys = JSONObject.toJSONString(messageKeys);
	}

	public Long getMessageExpires() {
		return messageExpires;
	}

	public void setMessageExpires(Long messageExpires) {
		this.messageExpires = messageExpires;
	}
}
