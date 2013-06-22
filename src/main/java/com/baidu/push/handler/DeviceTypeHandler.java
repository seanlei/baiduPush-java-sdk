package com.baidu.push.handler;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.model.DeviceType;
import com.baidu.push.response.PushResponse;

/**
 * 根据channel_id查询设备类型的处理函数
 * @author liyazhou@baidu.com
 *
 */
public class DeviceTypeHandler extends HttpResponseHandler<DeviceType>{
	@Override
	protected boolean hasResponseParams() {
		return true;
	}
	
	@Override
	public PushResponse<DeviceType> handleResponseParams(JSONObject paramsObj) {
		PushResponse<DeviceType> response = new PushResponse<DeviceType>();
		
		int deviceType = paramsObj.getIntValue("device_type");
		response.setResult(DeviceType.valueOf(deviceType));
		
		return response;
	}
}
