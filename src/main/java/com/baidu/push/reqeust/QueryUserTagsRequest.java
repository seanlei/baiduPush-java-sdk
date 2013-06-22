package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class QueryUserTagsRequest extends UserRelatedRequest{
	
	public QueryUserTagsRequest(String userId) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.query_user_tags);
		super.setUserId(userId);
	}
}


