package com.baidu.push.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * 创建http请求
 * @author liyazhou@baidu.com
 *
 */
public class HttpClientFactory {
	public HttpClient createHttpClient() {
		HttpParams httpClientParams = new BasicHttpParams();
		// HttpProtocolParams.setUserAgent(httpClientParams, userAgent);
		HttpConnectionParams.setConnectionTimeout(httpClientParams, 50000);
		HttpConnectionParams.setSoTimeout(httpClientParams,50000);
		HttpConnectionParams.setStaleCheckingEnabled(httpClientParams, true);
		HttpConnectionParams.setTcpNoDelay(httpClientParams, true);


		ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
		connectionManager.setDefaultMaxPerRoute(50);
		connectionManager.setMaxTotal(50);

		DefaultHttpClient httpClient = new DefaultHttpClient(connectionManager, httpClientParams);

		return httpClient;
	}

}
