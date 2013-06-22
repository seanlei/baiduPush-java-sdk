package com.baidu.push.model;

/**
 * 推送类型，取值范围为：1～3
 * 1：单个人，必须指定user_id 和 channel_id （指定用户的指定设备）或者user_id（指定用户的所有设备）
 * 2：一群人，必须指定 tag
 * 3：所有人，无需指定tag、user_id、channel_id
 * 
 * @author liyazhou@baidu.com
 *
 */
public enum PushType {
	empty,
	single_user,
	tag_user,
	all_user;
}
