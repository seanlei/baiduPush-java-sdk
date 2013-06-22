package com.baidu.push.reqeust;

/**
 * @author liyazhou@baidu.com
 *
 */
public class UserRelatedRequest extends BaiduPushRequest{
	/**
	 * 用户标识。不超过256字节。如果存在此字段，则只返回与该用户相关的绑定关系
	 */
	private String userId = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
