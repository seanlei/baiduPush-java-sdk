package com.baidu.push.model;

import java.io.Serializable;

/**
 * 查询设备绑定关系的返回实体
 * @author liyazhou@baidu.com
 *
 */
public class QueryBindListItem implements Serializable{
	private static final long serialVersionUID = -1151427103313143297L;
	
	/**
	 * 通道标识，系统返回的channel_id。
	 */
	private String channelId;  
	/**
	 * channel绑定的user标识。
	 */
	private String userId;  
	/**
	 * channel绑定的设备编号。
	 */
	private String deviceId; 
	/**
	 * channel绑定的设备类型。
	 */
	private int deviceType; 
	/**
	 * channel绑定的设备描述。
	 */
	private String deviceName; 
	/**
	 * channel绑定名称。
	 */
	private String bindName; 
	/**
	 * channel绑定时间。
	 */
	private String bindTime; 
	/**
	 * channel绑定附加信息。
	 */
	private String infoString;  
	/**
	 * 绑定状态，0：绑定在线； 1：绑定离线。
	 */
	private int bindStatus; 
	/**
	 * 应用在线状态，on:在线；off:离线。
	 */
	private String onlineStatus; 
	/**
	 * 连接创建时间，仅在在线状态时返回。
	 */
	private long onlineTimestamp; 
	/**
	 * 连接超时时，仅在在线状态时返回。
	 */
	private long onlineExpires; 
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public String getInfoString() {
		return infoString;
	}

	public void setInfoString(String infoString) {
		this.infoString = infoString;
	}

	public int getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(int bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public long getOnlineTimestamp() {
		return onlineTimestamp;
	}

	public void setOnlineTimestamp(long onlineTimestamp) {
		this.onlineTimestamp = onlineTimestamp;
	}

	public long getOnlineExpires() {
		return onlineExpires;
	}

	public void setOnlineExpires(long onlineExpires) {
		this.onlineExpires = onlineExpires;
	}

	@Override
	public String toString() {
		return "QueryBindListItem [channelId=" + channelId + ", userId="
				+ userId + ", deviceId=" + deviceId + ", deviceType="
				+ deviceType + ", deviceName=" + deviceName + ", bindName="
				+ bindName + ", bindTime=" + bindTime + ", infoString="
				+ infoString + ", bindStatus=" + bindStatus + ", onlineStatus="
				+ onlineStatus + ", onlineTimestamp=" + onlineTimestamp
				+ ", onlineExpires=" + onlineExpires + "]";
	}
}
