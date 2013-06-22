package com.baidu.push.reqeust;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.util.Constants;

/**
 * @author liyazhou@baidu.com
 *
 */
public class FetchTagRequest extends PagableRequest{
	/**
	 * 标签名称。
	 */
	private String name;
	
	public FetchTagRequest() {
		super.methodName = Constants.fetch_tag;
		super.httpMethod = HttpMethodName.POST;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


