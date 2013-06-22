package com.baidu.push.model;

/**
 * 点击通知后的行为(
 * 1: 打开Url;
 * 2: 自定义行为;
 * 3：其它值则默认打开应用);
 * @author liyazhou@baidu.com
 *
 */
public enum OpenType {
	empty,
	open_url,
	user_define,
	open_app;
}
