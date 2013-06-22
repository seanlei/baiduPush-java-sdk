package com.baidu.push.handler;

import java.util.Iterator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.push.model.QueryBindListItem;
import com.baidu.push.model.QueryBindListResponseBean;
import com.baidu.push.response.PushResponse;

/**
 * 查询设备、应用、用户与百度Channel的绑定关系的处理类
 * @author liyazhou@baidu.com
 *
 */
public class QueryBindListResponseHandler extends HttpResponseHandler<QueryBindListResponseBean>{
	@Override
	protected boolean hasResponseParams() {
		return true;
	}
	
	@Override
	public PushResponse<QueryBindListResponseBean> handleResponseParams(JSONObject paramsObj) {
		PushResponse<QueryBindListResponseBean> response = new PushResponse<QueryBindListResponseBean>();
		
		QueryBindListResponseBean reponseBean = new QueryBindListResponseBean();
		
		reponseBean.setTotalNum(paramsObj.getIntValue("total_num"));
		reponseBean.setAmount(paramsObj.getIntValue("amount"));
		
		response.setResult(reponseBean);
		
		JSONArray bindsArray = paramsObj.getJSONArray("binds");
		if(bindsArray.isEmpty()){
			return response;
		}
		
		Iterator<Object> iter = bindsArray.iterator();
		while(iter.hasNext()){
			JSONObject obj = (JSONObject)iter.next();
			QueryBindListItem item = new QueryBindListItem();
			
			item.setChannelId(obj.getString("channel_id"));
			item.setUserId(obj.getString("user_id"));
			item.setDeviceId(obj.getString("device_id"));
			item.setDeviceType(obj.getIntValue("device_type"));
			item.setDeviceName(obj.getString("device_name"));
			item.setBindName(obj.getString("bind_name"));
			item.setBindTime(obj.getString("bind_time"));
			item.setInfoString(obj.getString("info"));
			item.setBindStatus(obj.getIntValue("bind_status"));
			item.setOnlineStatus(obj.getString("online_status"));
			item.setOnlineTimestamp(obj.getLongValue("online_timestamp"));
			item.setOnlineExpires(obj.getLongValue("online_expires"));
			
			reponseBean.addItem(item);
		}
		
		return response;
	}

}
