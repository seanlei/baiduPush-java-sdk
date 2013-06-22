package com.baidu.push.reqeust;

import java.util.List;

import com.baidu.push.http.HttpMethodName;
import com.baidu.push.model.DeviceType;

/**
 * @author liyazhou@baidu.com
 *
 */
public abstract class BaiduPushRequest {
	public final static int DEFAULT_EXPIRES = 0;
	public final static int DEFAULT_START = 0;
	public final static int DEFAULT_LIMIT = 10;
	/**
	 * API的资源操作方法名
	 */
	protected String methodName = null;
	/**
	 * 通道标识
	 */
	protected String channelId = null;
	/**
	 * Http请求的方式，比如GET、POST
	 */
	protected HttpMethodName httpMethod = null;
	
	/**
	 * 使用http还是https请求
	 */
	protected boolean needSsl;
	
	
	/**
	 * 如果存在此字段，则只返回该设备类型的绑定关系。 默认不区分设备类型。
	 * 百度Channel支持多种设备，各种设备的类型编号如下：（支持某种组合，如：1,2,4:）
	 * 
	 * @see DeviceType
	 */
	private List<DeviceType> deviceTypes = null;
	
	/**
	 * 用户指定本次请求签名的失效时间。格式为unix时间戳形式。
	 */
	private int expires = DEFAULT_EXPIRES;
	
	public BaiduPushRequest() {}

	public BaiduPushRequest(String methodName, String channelId,
			HttpMethodName httpMethod) {
		this.methodName = methodName;
		this.channelId = channelId;
		this.httpMethod = httpMethod;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public HttpMethodName getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethodName httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	public List<DeviceType> getDeviceTypes() {
		return deviceTypes;
	}

	public void setDeviceTypes(List<DeviceType> deviceTypes) {
		this.deviceTypes = deviceTypes;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public boolean isNeedSsl() {
		return needSsl;
	}

	public void setNeedSsl(boolean needSsl) {
		this.needSsl = needSsl;
	}
}
