package com.baidu.push.util;

/**
 * 系统常量
 * @author liyazhou@baidu.com
 *
 */
public class Constants {
	public final static String HTTP = "http";
	public final static String HTTPS = "https";
	
	public final static String PUSH_URL = "://channel.api.duapp.com/rest/2.0/channel/";
	
	public final static String DEFAULT_ENCODING = "utf-8";
	public final static String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";
	public final static String NULL_VERSION_ID = "null";
	public final static int KB = 1024;
	public final static int MB = 1048576;
	public final static long GB = 1073741824L;
	
	public final static String CHANNEL = "channel";
	public final static String SIGN = "sign";
	
	public final static String query_bindlist = "query_bindlist";
	public final static String push_msg = "push_msg";
	public final static String verify_bind = "verify_bind";
	public final static String fetch_msg = "fetch_msg";
	public final static String fetch_msgcount = "fetch_msgcount";
	public final static String delete_msg = "delete_msg";
	public final static String set_tag = "set_tag";
	public final static String delete_tag = "delete_tag";
	public final static String fetch_tag = "fetch_tag";
	public final static String query_user_tags = "query_user_tags";
	public final static String init_app_ioscert = "init_app_ioscert";
	public final static String update_app_ioscert = "update_app_ioscert";
	public final static String delete_app_ioscert = "delete_app_ioscert";
	public final static String query_app_ioscert = "query_app_ioscert";
	public final static String query_device_type = "query_device_type";
	
}