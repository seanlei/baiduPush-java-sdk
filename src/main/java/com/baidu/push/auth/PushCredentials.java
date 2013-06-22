package com.baidu.push.auth;

/**
 * AK和SK是应用访问资源或服务的唯一凭证。 查看AK和SK信息
 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/guide">官方文档</a>
 * 
 * @author liyazhou@baidu.com
 *
 */
public class PushCredentials {

	private String accessKey;
	private String secretKey;

	public PushCredentials(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	public String getAccessKey() {
		return this.accessKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}
}
