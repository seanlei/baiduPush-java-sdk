package com.baidu.push.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.baidu.push.reqeust.BaiduPushRequest;
import com.baidu.push.util.Constants;
import com.baidu.push.util.PushClientException;

/**
 * httpRequest的工厂类
 * @author liyazhou@baidu.com
 *
 */
public class HttpRequestFactory {
	public HttpRequestBase createHttpRequestBase(PushHttpRequest pushHttpRequest) {
		BaiduPushRequest pushRequest = pushHttpRequest.getOriginalRequest();
		
		// uri
		StringBuilder uriBuilder = new StringBuilder();
		if(pushRequest.isNeedSsl()){
			uriBuilder.append(Constants.HTTPS);
		} else {
			uriBuilder.append(Constants.HTTP);
		}
		
		uriBuilder.append(Constants.PUSH_URL);

		String channelId = pushRequest.getChannelId();
		if (channelId == null) {
			uriBuilder.append(Constants.CHANNEL);
		} else {
			uriBuilder.append(channelId);
		}

		HttpRequestBase httpRequest;
		// new request
		if (pushHttpRequest.getHttpMethod() == HttpMethodName.GET) {
			StringEntity strEntity = this.encodeParameters(pushHttpRequest);
			if (strEntity != null) {
				String encodedParams;
				try {
					encodedParams = EntityUtils.toString(strEntity);
					uriBuilder.append("?").append(encodedParams);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpRequest = new HttpGet(uriBuilder.toString());
		} else if (pushHttpRequest.getHttpMethod() == HttpMethodName.POST) {
			HttpPost postMethod = new HttpPost(uriBuilder.toString());
			UrlEncodedFormEntity strEntity = this
					.encodeParameters(pushHttpRequest);
			strEntity.setContentType(Constants.DEFAULT_CONTENT_TYPE);
			if (strEntity != null) {
				postMethod.setEntity(strEntity);
			}
			httpRequest = postMethod;
		} else {
			throw new PushClientException("Unknown HTTP method name:"
					+ pushHttpRequest.getHttpMethod().toString());
		}

		for (Map.Entry<String, String> entry : pushHttpRequest.getHeaders()
				.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("Content-Length")) {
				continue;
			}
			httpRequest.addHeader(entry.getKey(), entry.getValue());
		}
		if ((httpRequest.getHeaders("Content-Type") == null)
				|| (httpRequest.getHeaders("Content-Type").length == 0)) {
			httpRequest.addHeader("Content-Type",
					Constants.DEFAULT_CONTENT_TYPE);
		}
		return httpRequest;
	}

	private UrlEncodedFormEntity encodeParameters(PushHttpRequest request) {
		List<BasicNameValuePair> nameValuePairs = null;
		if (request.getParameters().size() > 0) {
			Map<String, String> params = request.getParameters();
			String sign = params.remove(Constants.SIGN);
			nameValuePairs = new ArrayList<BasicNameValuePair>(request
					.getParameters().size());
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue() + ""));
			}
			nameValuePairs.add(new BasicNameValuePair(Constants.SIGN, sign));
		}
		if (nameValuePairs != null) {
			try {
				return new UrlEncodedFormEntity(nameValuePairs,
						Constants.DEFAULT_ENCODING);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
