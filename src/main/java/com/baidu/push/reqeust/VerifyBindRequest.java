package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class VerifyBindRequest extends UserRelatedRequest{
	public VerifyBindRequest(String userId) {
		super.setHttpMethod(HttpMethodName.POST);
		super.setMethodName(Constants.verify_bind);
		super.setUserId(userId);
	}
}
