package com.baidu.push.http;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import com.baidu.push.auth.PushCredentials;
import com.baidu.push.auth.PushSigner;
import com.baidu.push.handler.ErrorResponseHandler;
import com.baidu.push.handler.HttpResponseHandler;
import com.baidu.push.reqeust.BaiduPushRequest;
import com.baidu.push.response.PushResponse;
import com.baidu.push.util.PushClientException;
import com.baidu.push.util.PushServiceException;

/**
 * httpRequest的发送者
 * @author liyazhou@baidu.com
 *
 */
public class PushHttpClient {
	private static final Log log = LogFactory.getLog(PushHttpClient.class);
	
	private HttpClient httpClient;
	private HttpRequestFactory httpRequestFactory = new HttpRequestFactory();
	private HttpClientFactory clientFactory = new HttpClientFactory();
	private ErrorResponseHandler<PushServiceException> errorResponseHandler = new ErrorResponseHandler<PushServiceException>();
	private PushCredentials credentials;
	
	public PushHttpClient(PushCredentials credentials) {
		this.credentials = credentials;
	}

	private PushHttpResponse createPushHttpResponse(HttpResponse httpResponse) throws IllegalStateException, IOException {
		PushHttpResponse pushHttpResponse = new PushHttpResponse();

		HttpEntity entity = httpResponse.getEntity();
		if (null != entity) {
			String body = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			
			pushHttpResponse.setContent(body);
		}
		
		pushHttpResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
		pushHttpResponse.setStatusText(httpResponse.getStatusLine().getReasonPhrase());

		for (Header header : httpResponse.getAllHeaders()) {
			pushHttpResponse.addHeader(header.getName(), header.getValue());
		}
		return pushHttpResponse;
	}
	
	public <T> PushResponse<T> execute(PushHttpRequest pushHttpRequest, 
			HttpResponseHandler<T> responseHandler) {
		BaiduPushRequest originalRequest = pushHttpRequest.getOriginalRequest();
		pushHttpRequest.setHttpMethod(originalRequest.getHttpMethod());
		
		// sign
		log.trace("begin sign");
		PushSigner.sign(pushHttpRequest.getOriginalRequest(), pushHttpRequest, credentials);
		log.trace("after sign");
		
		log.info("httpRequest:"+pushHttpRequest);
		HttpRequestBase httpRequest = this.httpRequestFactory.createHttpRequestBase(pushHttpRequest);
		this.httpClient = this.clientFactory.createHttpClient();
		HttpResponse httpResponse;
		try {
			httpResponse = this.httpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			throw new PushClientException("Send to server failed.", e);
		} catch (IOException e) {
			throw new PushClientException("Send to server failed.", e);
		}
		try {
			if (this.isRequestSuccessful(httpResponse)) {
				return this.handleHttpResponse(httpResponse, responseHandler);
			} else {
				throw this.handleErrorHttpResponse(httpResponse, this.errorResponseHandler).getResult();
			}
		} catch (IllegalStateException e) {
			throw new PushClientException("Handle http response failed.", e);
		} catch (IOException e) {
			throw new PushClientException("Handle http response failed.", e);
		} finally {
			/*
			 * Some response handlers need to manually manage the HTTP
			 * connection and will take care of releasing the connection on
			 * their own, but if this response handler doesn't need the
			 * connection left open, we go ahead and release the it to free up
			 * resources.
			 */
			if (!responseHandler.isNeedsConnectionLeftOpen()) {
				try {
					httpResponse.getEntity().getContent().close();
				} catch (Throwable t) {

				}
			}

		}

	}
	
	private PushResponse<PushServiceException> handleErrorHttpResponse(HttpResponse httpResponse,
			ErrorResponseHandler<PushServiceException> errorResponseHandler) throws IllegalStateException, IOException {
		PushHttpResponse pushHttpResponse = this.createPushHttpResponse(httpResponse);
		return errorResponseHandler.handle(pushHttpResponse);
	}

	private <T> PushResponse<T> handleHttpResponse(HttpResponse httpResponse, HttpResponseHandler<T> responseHandler)
			throws IllegalStateException, IOException {
		PushHttpResponse pushHttpResponse = this.createPushHttpResponse(httpResponse);
		return responseHandler.handle(pushHttpResponse);
	}
	
	private boolean isRequestSuccessful(HttpResponse response) {
		int status = response.getStatusLine().getStatusCode();
		return (status / 100) == 2;
	}
}
