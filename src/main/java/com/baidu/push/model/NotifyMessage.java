package com.baidu.push.model;

/**
 * 当消息类型为notify的时候需要额外设置的一些值
 * http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/faq
 * $message = "{ 
 *  'title': 'title',
 *   'description': 'description',
 *   'notification_builder_id': 0,
 *   'notification_basic_style': 0x04,
 *   'open_type':2,  
 *   'custom_content':{'type_code':'1','type_content':'7448969'} 
 *   }";
 * @author liyazhou@baidu.com
 *
 */
public class NotifyMessage {
	/**
	 * 通知标题，可以为空；如果为空则设为appid对应的应用名;
	 */
	private String title;
	/**
	 * 通知文本内容，不能为空;
	 */
	private String description;
	
	/**
	 * android客户端自定义通知样式，如果没有设置默认为0;
	 */
	private int notification_builder_id;
	/**
	 * 只有notification_builder_id为0时才有效，才需要设置；
	 * 该属性是整型，每一位代表一种基本样式，基本样式用二进制位表示如下
	 * 响铃：0100B=0x04
	 * 振动：0010B=0x02
	 * 可清除：0001B=0x01
	 * 如果需要同时设置多种基本样式，可以对上述三种基本样式做“或”运算，例如要设置通知为响铃、振动和可清除、
	 * 则notification_basic_style 值为：
	 * notification_basic_style=0100B | 0010B | 0001B= 0111B=0x07
	 */
	private int notification_basic_style = 7;
	
	/**
	 * 点击通知后的行为(1: 打开Url;2: 自定义行为;3：其它值则默认打开应用);
	 */
	private OpenType openType;
	
	/**
	 * url只有open_type为1时才有效，才需要设置，如果open_type为1则可以设置需要打开的Url地址;
	 */
	private String url;
	
	/**
	 * 只有open_type为1时才有效，才需要设置,(需要请求用户授权：1；默认直接打开：0),
	 */
	private UserConfirm userConfirm;
	
	/**
	 * 只有open_type为2时才有效，才需要设置, 
	 * 如果open_type为2则可以设置自定义打开行为(具体参考管理控制台文档);
	 */
	private String pkgContet;
	
	/**
	 * 自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递。
	 */
	private String customConent;
	
	public NotifyMessage(String description){
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNotification_builder_id() {
		return notification_builder_id;
	}

	public void setNotification_builder_id(int notification_builder_id) {
		this.notification_builder_id = notification_builder_id;
	}

	public int getNotification_basic_style() {
		return notification_basic_style;
	}

	public void setNotification_basic_style(int notification_basic_style) {
		this.notification_basic_style = notification_basic_style;
	}

	public OpenType getOpenType() {
		return openType;
	}

	public void setOpenType(OpenType openType) {
		this.openType = openType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UserConfirm getUserConfirm() {
		return userConfirm;
	}

	public void setUserConfirm(UserConfirm userConfirm) {
		this.userConfirm = userConfirm;
	}

	public String getPkgContet() {
		return pkgContet;
	}

	public void setPkgContet(String pkgContet) {
		this.pkgContet = pkgContet;
	}

	public String getCustomConent() {
		return customConent;
	}

	public void setCustomConent(String customConent) {
		this.customConent = customConent;
	}
}
