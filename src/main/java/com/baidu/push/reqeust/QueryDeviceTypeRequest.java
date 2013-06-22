package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class QueryDeviceTypeRequest extends BaiduPushRequest{
	public QueryDeviceTypeRequest(String channelId) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.query_device_type);
		
		super.setChannelId(channelId);
	}
}
