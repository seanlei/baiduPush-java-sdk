package com.baidu.push.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.push.model.Message;
import com.baidu.push.model.MessageType;
import com.baidu.push.response.PushResponse;

/**
 * 查询离线消息的处理函数
 * @author liyazhou@baidu.com
 *
 */
public class FetchMessageResponseHandler extends HttpResponseHandler<List<Message>>{
	@Override
	protected boolean hasResponseParams() {
		return true;
	}
	
	@Override
	public PushResponse<List<Message>> handleResponseParams(JSONObject paramsObj) {
		PushResponse<List<Message>> response = new PushResponse<List<Message>>();
		
		JSONArray messagesArray = paramsObj.getJSONArray("messages");
		if(messagesArray.isEmpty()){
			response.setResult(Collections.<Message>emptyList());
			return response;
		}
		
		List<Message> messages = new ArrayList<Message>();
		Iterator<Object> iter = messagesArray.iterator();
		while(iter.hasNext()){
			JSONObject obj = (JSONObject)iter.next();
			Message message = new Message();
			
			message.setChannelId(obj.getString("channel_id"));
			message.setMessageId(obj.getString("msg_id"));
			message.setMessageKey(obj.getString("msg_key"));
			message.setMessage(obj.getString("msg"));
			message.setMessageLength(obj.getIntValue("message_length"));
			message.setMessageType(MessageType.values()[obj.getIntValue("message_type")]);
			message.setMessageExpires(obj.getIntValue("message_expires"));
			
			messages.add(message);
		}
		response.setResult(messages);
		
		return response;
	}
}
