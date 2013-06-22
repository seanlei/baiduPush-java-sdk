package com.baidu.push.handler;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.response.PushResponse;

/**
 * 查询离线消息的个数处理函数
 * @author liyazhou@baidu.com
 *
 */
public class FetchMessageCountResponseHandler extends HttpResponseHandler<Integer>{
	@Override
	protected boolean hasResponseParams() {
		return true;
	}
	
	@Override
	public PushResponse<Integer> handleResponseParams(JSONObject paramsObj) {
		PushResponse<Integer> response = new PushResponse<Integer>();
		
		int msgCount = paramsObj.getIntValue("total_num");
		response.setResult(msgCount);
		
		return response;
	}
}
