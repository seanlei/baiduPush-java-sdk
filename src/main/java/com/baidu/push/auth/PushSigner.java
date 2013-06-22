package com.baidu.push.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baidu.push.http.PushHttpRequest;
import com.baidu.push.reqeust.BaiduPushRequest;
import com.baidu.push.util.Constants;
import com.baidu.push.util.PushClientException;

/**
 * 生成签名算法
 * 
 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api">官方文档</a>
 * 
 * @author liyazhou@baidu.com
 *
 */
public class PushSigner {
	private static final Log log = LogFactory.getLog(PushSigner.class);
	
	private static String urlencode(String str) throws UnsupportedEncodingException {
		String rc = URLEncoder.encode(str, "utf-8");
		return rc.replace("*", "%2A");
	}
	
	public static void sign(BaiduPushRequest pushRequest,PushHttpRequest httpRequest, PushCredentials credentials) {
		StringBuilder signContents = new StringBuilder();
		// generate sign content
		if (null == pushRequest.getHttpMethod()) {
			throw new PushClientException("Sign failed! Param: httpMethod can not be empty!");
		}
		
		signContents.append(pushRequest.getHttpMethod().toString());
		if(pushRequest.isNeedSsl()){
			signContents.append(Constants.HTTPS);
		} else {
			signContents.append(Constants.HTTP);
		}
		signContents.append(Constants.PUSH_URL);
		
		if(pushRequest.getChannelId() == null){
			signContents.append(Constants.CHANNEL);
		} else {
			signContents.append(pushRequest.getChannelId());
		}
		Map<String,String> sinParameters = httpRequest.getParameters();
		for(Map.Entry<String, String> i : sinParameters.entrySet()) {
			signContents.append(i.getKey());
			signContents.append('=');
			signContents.append(i.getValue());
		}
		signContents.append(credentials.getSecretKey());
		log.info("signContents:"+signContents);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			//md.update( URLEncoder.encode(sb.toString(), "utf-8").getBytes() );
			md.update(urlencode(signContents.toString()).getBytes() );
			byte[] md5  = md.digest();
			
			signContents.setLength(0);
			for(byte b : md5) {
				signContents.append( String.format("%02x", b & 0xff));
			}
			
			httpRequest.addParameter("sign", signContents.toString());
		} catch (NoSuchAlgorithmException e) {
			log.error("error:"+e);
			throw new PushClientException("Sign failed! reason:"+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error("error:"+e);
			throw new PushClientException("Sign failed! reason:"+e.getMessage());
		}
	}
	
}
