package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class FetchMessageRequest extends UserRelatedRequest{
	public FetchMessageRequest(String userId) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.fetch_msg);
		super.setUserId(userId);
	}
}

