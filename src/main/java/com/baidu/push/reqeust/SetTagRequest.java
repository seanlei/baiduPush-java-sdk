package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class SetTagRequest extends BaiduPushRequest{
	private String tag;
	
	public SetTagRequest(String tag) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.set_tag);
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}

