package com.baidu.push.model;

/**
 * 设备类型
 * 百度Channel支持多种设备，各种设备的类型编号如下：
 * 1：浏览器设备；
 * 2：PC设备；
 * 3：Andriod设备；
 * 4：iOS设备；
 * 5：Windows Phone设备；
 * @author liyazhou@baidu.com
 *
 */
public enum DeviceType {
	empty,
	browser,
	pc,
	android,
	iso,
	win_phone;
    
    public static DeviceType valueOf(int value){
    	DeviceType[] types = DeviceType.values();
    	for(DeviceType type : types){
    		if(value == type.ordinal()){
    			return type;
    		}
    	}
    	return empty;
    }
}
