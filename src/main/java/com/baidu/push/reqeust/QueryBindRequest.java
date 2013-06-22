package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class QueryBindRequest extends PagableRequest{
	private String userId;
	
	public QueryBindRequest() {
		super.methodName = Constants.query_bindlist;
		super.httpMethod = HttpMethodName.POST;
	}
	
	public QueryBindRequest(HttpMethodName methodName){
		this();
		super.httpMethod = methodName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
