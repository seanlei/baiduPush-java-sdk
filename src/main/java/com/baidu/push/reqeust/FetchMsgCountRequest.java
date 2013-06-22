package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class FetchMsgCountRequest extends UserRelatedRequest{
	public FetchMsgCountRequest(String userId) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.fetch_msgcount);
		super.setUserId(userId);
	}
}


