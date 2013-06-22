package com.baidu.push.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.http.PushHttpResponse;
import com.baidu.push.response.PushResponse;
import com.baidu.push.util.PushServiceException;

/**
 * 错误处理函数
 * @author liyazhou@baidu.com
 *
 */
public class ErrorResponseHandler<T> {
	private static final Log log = LogFactory.getLog(ErrorResponseHandler.class);
	
	public PushResponse<PushServiceException> handle(PushHttpResponse httpResponse) {
		String errorMsgJson = httpResponse.getContent();
		log.error("server error msg:" + errorMsgJson);
		
		PushResponse<PushServiceException> baiduPushResponse = new PushResponse<PushServiceException>();
		String requestId = "";
		int errorCode = -1;
		String errorMsg = "";

		if (!errorMsgJson.isEmpty()) {
			JSONObject json = JSONObject.parseObject(errorMsgJson);
			requestId = json.getString("request_id");
			errorCode = json.getIntValue("error_code");
			errorMsg = json.getString("error_msg");
		}
		PushServiceException exception = new PushServiceException("[StatusCode:" + httpResponse.getStatusCode() + "] [ErrorMsg:" + errorMsgJson + "]");
		exception.setRequestId(requestId);
		exception.setPushErrorCode(errorCode);
		exception.setPushErrorMessage(errorMsg);
		baiduPushResponse.setResult(exception);
		
		return baiduPushResponse;
	}
}
