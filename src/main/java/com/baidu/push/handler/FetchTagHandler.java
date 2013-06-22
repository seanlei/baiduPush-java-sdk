package com.baidu.push.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.push.model.FetchTagBean;
import com.baidu.push.model.Tag;
import com.baidu.push.response.PushResponse;

/**
 * 查询应用标签的处理类
 * @author liyazhou@baidu.com
 *
 */
public class FetchTagHandler extends HttpResponseHandler<FetchTagBean>{
	@Override
	protected boolean hasResponseParams() {
		return true;
	}
	
	@Override
	public PushResponse<FetchTagBean> handleResponseParams(JSONObject paramsObj) {
		PushResponse<FetchTagBean> response = new PushResponse<FetchTagBean>();
		
		FetchTagBean tagBean = new FetchTagBean();
		
		int totalNum = paramsObj.getIntValue("total_num");
		int count = paramsObj.getIntValue("amount");
		
		tagBean.setTotalNum(totalNum);
		tagBean.setCount(count);
		
		JSONArray tagsArray = paramsObj.getJSONArray("tags");
		if(tagsArray.isEmpty()){
			tagBean.setTags(Collections.<Tag>emptyList());
			response.setResult(tagBean);
			return response;
		}
		
		List<Tag> tags = new ArrayList<Tag>();
		Iterator<Object> iter = tagsArray.iterator();
		while(iter.hasNext()){
			JSONObject obj = (JSONObject)iter.next();
			Tag tag = new Tag();
			
			tag.setTid(obj.getString("tid"));
			tag.setName(obj.getString("name"));
			tag.setInfo(obj.getString("info"));
			tag.setType(obj.getIntValue("type"));
			tag.setCreateTime(obj.getLongValue("create_time"));
			
			tags.add(tag);
		}
		tagBean.setTags(tags);
		
		response.setResult(tagBean);
		
		return response;
	}
}