package com.baidu.push.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.http.PushHttpResponse;
import com.baidu.push.response.PushResponse;

/**
 * 通用的处理类
 * @author liyazhou@baidu.com
 *
 */
public abstract class HttpResponseHandler<T> {
	private static final Log log = LogFactory.getLog(HttpResponseHandler.class);
	
	protected abstract boolean hasResponseParams();
	
	protected  PushResponse<T> handleResponseParams(JSONObject paramsObj){
		return null;
	}
	
		
	public PushResponse<T> handle(PushHttpResponse httpResponse){
		log.trace("handle:"+httpResponse);
		
		String content = httpResponse.getContent();
		log.info("server response:"+content);
		
		JSONObject json = JSONObject.parseObject(content);
		
		String requestId = json.getString("request_id");
		
		
		boolean hasResponseParams = hasResponseParams();
		PushResponse<T> response = null;
		if(hasResponseParams){
			JSONObject paramsObj = json.getJSONObject("response_params");
			response = handleResponseParams(paramsObj);
		} else {
			response = new PushResponse<T>();
		}
		
		response.setRequestId(requestId);
		
		return response;
	}


	/**
	 * @return the needsConnectionLeftOpen
	 */
	public boolean isNeedsConnectionLeftOpen() {
		return false;
	}
}
