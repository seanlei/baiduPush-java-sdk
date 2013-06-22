package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.model.IosCert;

/**
 * @author liyazhou@baidu.com
 *
 */
public class IosCertRequest extends BaiduPushRequest{
	private IosCert isoCert;
	
	public IosCertRequest() {}
	
	public IosCertRequest(IosCert IosCert) {
		super.setHttpMethod(HttpMethodName.POST);
		this.isoCert = IosCert;
	}

	public IosCert getIsoCert() {
		return isoCert;
	}

	public void setIsoCert(IosCert isoCert) {
		this.isoCert = isoCert;
	}
}
