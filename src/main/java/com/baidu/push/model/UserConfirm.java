package com.baidu.push.model;

/**
 * 定义notify的消息时候当open_type为1时需要设置,
 * 0 默认直接打开
 * 1 需要请求用户授权
 * 如果open_type为1 则可以设置打开的Url地址时是否请求用户授权;
 * @author liyazhou@baidu.com
 *
 */
public enum UserConfirm {
	no_confirm,
	need_confirm;
}
