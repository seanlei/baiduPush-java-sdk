package com.baidu.push;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.push.auth.PushCredentials;
import com.baidu.push.handler.DeleteMessageHandler;
import com.baidu.push.handler.DeviceTypeHandler;
import com.baidu.push.handler.FetchMessageCountResponseHandler;
import com.baidu.push.handler.FetchMessageResponseHandler;
import com.baidu.push.handler.FetchTagHandler;
import com.baidu.push.handler.PushMessageResponseHandler;
import com.baidu.push.handler.QueryBindListResponseHandler;
import com.baidu.push.handler.QueryIosCertHandler;
import com.baidu.push.handler.VoidResponseHandler;
import com.baidu.push.http.DefaultPushHttpRequest;
import com.baidu.push.http.HttpMethodName;
import com.baidu.push.http.PushHttpClient;
import com.baidu.push.http.PushHttpRequest;
import com.baidu.push.model.DeleteMessageResponseBean;
import com.baidu.push.model.DeviceType;
import com.baidu.push.model.Empty;
import com.baidu.push.model.FetchTagBean;
import com.baidu.push.model.IosCert;
import com.baidu.push.model.Message;
import com.baidu.push.model.PushType;
import com.baidu.push.model.QueryBindListResponseBean;
import com.baidu.push.reqeust.BaiduPushRequest;
import com.baidu.push.reqeust.DeleteMsgRequest;
import com.baidu.push.reqeust.DeleteTagRequest;
import com.baidu.push.reqeust.FetchMessageRequest;
import com.baidu.push.reqeust.FetchMsgCountRequest;
import com.baidu.push.reqeust.FetchTagRequest;
import com.baidu.push.reqeust.IosCertRequest;
import com.baidu.push.reqeust.PagableRequest;
import com.baidu.push.reqeust.PushMessageRequest;
import com.baidu.push.reqeust.QueryBindRequest;
import com.baidu.push.reqeust.QueryDeviceTypeRequest;
import com.baidu.push.reqeust.QueryUserTagsRequest;
import com.baidu.push.reqeust.SetTagRequest;
import com.baidu.push.reqeust.VerifyBindRequest;
import com.baidu.push.response.PushResponse;
import com.baidu.push.util.PushClientException;
import com.baidu.push.util.PushUtil;

/**
 * 推送客户端实现
 * @author liyazhou@baidu.com
 *
 */
public class DefaultPushClient implements PushClient{
	private PushHttpClient pushHttpClient;
	private PushCredentials credentials;
	
	public DefaultPushClient(PushCredentials credentials){
		this.credentials = credentials;
		pushHttpClient = new PushHttpClient(credentials);
	}
	
	public DefaultPushClient(String accessKey, String secretKey){
		this.credentials = new PushCredentials(accessKey,secretKey);
		pushHttpClient = new PushHttpClient(credentials);
	}
	
	@Override
	public PushResponse<QueryBindListResponseBean> queryBind(QueryBindRequest request) {
		PushHttpRequest httpRequest = createHttpRequest(request);
		
		if(StringUtils.isNotBlank(request.getUserId())){
			httpRequest.addParameter("user_id", request.getUserId());
		}
		
		int start = request.getStart();
		if(start != QueryBindRequest.DEFAULT_START){
			httpRequest.addParameter("start", start);
		}
		
		int limit = request.getLimit();
		if(limit != QueryBindRequest.DEFAULT_LIMIT){
			httpRequest.addParameter("limit", limit);
		}
		
		
		return this.pushHttpClient.execute(httpRequest, new QueryBindListResponseHandler());
	}
	
	@Override
	public PushResponse<Integer> pushMessage(PushMessageRequest pushMsgReq){
		PushHttpRequest httpRequest = createHttpRequest(pushMsgReq);
		PushType pushType = pushMsgReq.getPushType();
		
		httpRequest.addParameter("message_type", pushMsgReq.getMessageType().ordinal());
		httpRequest.addParameter("messages", pushMsgReq.getMessages());
		httpRequest.addParameter("push_type", pushType.ordinal());
		httpRequest.addParameter("msg_keys", pushMsgReq.getMessageKeys());
		
		String tag = pushMsgReq.getTag();
		if(PushType.tag_user.equals(pushType)){
			if(StringUtils.isBlank(tag)){
				throw new PushClientException("发送给一群人时必须指定 tag!");
			}
			httpRequest.addParameter("tag", tag);
		}
		
		if(!PushMessageRequest.DEFAULT_MSG_EXPIRES.equals(
				pushMsgReq.getMessageExpires())){
			httpRequest.addParameter("message_expires", pushMsgReq.getMessageExpires());
		}
		
		if(StringUtils.isNotBlank(pushMsgReq.getUserId())){
			httpRequest.addParameter("user_id", pushMsgReq.getUserId());
		}
		
		return this.pushHttpClient.execute(httpRequest, new PushMessageResponseHandler());
	}
	
	@Override
	public PushResponse<Empty> veryBind(VerifyBindRequest req){
		PushHttpRequest httpRequest = createHttpRequest(req);
		httpRequest.addParameter("user_id", req.getUserId());
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<List<Message>> fetchMessage(FetchMessageRequest req){
		PushHttpRequest httpRequest = createHttpRequest(req);
		httpRequest.addParameter("user_id", req.getUserId());
		
		return this.pushHttpClient.execute(httpRequest, new FetchMessageResponseHandler());
	}
	
	@Override
	public PushResponse<Integer> fetchMessageCount(FetchMsgCountRequest req){
		PushHttpRequest httpRequest = createHttpRequest(req);
		httpRequest.addParameter("user_id", req.getUserId());
		
		return this.pushHttpClient.execute(httpRequest, new FetchMessageCountResponseHandler());
	}
	
	@Override
	public PushResponse<DeleteMessageResponseBean> deleteMessage(DeleteMsgRequest req){
		PushHttpRequest httpRequest = createHttpRequest(req);
		httpRequest.addParameter("user_id", req.getUserId());
		
		List<String> msgIds = req.getMsgIds();
		if(msgIds.size() == 1){
			httpRequest.addParameter("msg_ids", msgIds.get(0));
		} else {
			httpRequest.addParameter("msg_ids", JSONObject.toJSON(msgIds).toString());
		}
		
		return this.pushHttpClient.execute(httpRequest, new DeleteMessageHandler());
	}
	
	@Override
	public PushResponse<Empty> setTag(SetTagRequest req){
		PushHttpRequest httpRequest = createHttpRequest(req);
		httpRequest.addParameter("tag", req.getTag());
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<FetchTagBean> fetchTag(FetchTagRequest request){
		PushHttpRequest httpRequest = createHttpRequest(request);
		if(StringUtils.isNotBlank(request.getName())){
			httpRequest.addParameter("name", request.getName());
		}
		
		return this.pushHttpClient.execute(httpRequest, new FetchTagHandler());
	}
	
	@Override
	public PushResponse<Empty> deleteTag(DeleteTagRequest request){
		PushHttpRequest httpRequest = createHttpRequest(request);
		httpRequest.addParameter("tag", request.getTag());
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<FetchTagBean> queryUserTags(QueryUserTagsRequest request){
		PushHttpRequest httpRequest = createHttpRequest(request);
		httpRequest.addParameter("user_id", request.getUserId());
		
		return this.pushHttpClient.execute(httpRequest, new FetchTagHandler());
	}
	
	@Override
	public PushResponse<DeviceType> queryDeviceType(QueryDeviceTypeRequest request){
		PushHttpRequest httpRequest = createHttpRequest(request);
		httpRequest.addParameter("channel_id", request.getChannelId());
		
		return this.pushHttpClient.execute(httpRequest, new DeviceTypeHandler());
	}
	
	@Override
	public PushResponse<Empty> initIosCert(IosCertRequest request){
		request.setMethodName("init_app_ioscert");
		PushHttpRequest httpRequest = createHttpRequest(request);
		
		
		IosCert cert = request.getIsoCert();
		
		httpRequest.addParameter("name", cert.getName());
		httpRequest.addParameter("description", cert.getDescription());
		httpRequest.addParameter("release_cert", cert.getReleaseCert());
		httpRequest.addParameter("dev_cert", cert.getDevCert());
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<Empty> updateIosCert(IosCertRequest request){
		request.setMethodName("update_app_ioscert");
		PushHttpRequest httpRequest = createHttpRequest(request);
		
		
		setCert(request.getIsoCert(), httpRequest);
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<Empty> deleteIosCert(IosCertRequest request){
		request.setHttpMethod(HttpMethodName.POST);
		request.setMethodName("delete_app_ioscert");
		
		PushHttpRequest httpRequest = createHttpRequest(request);
		
		
		return this.pushHttpClient.execute(httpRequest, new VoidResponseHandler());
	}
	
	@Override
	public PushResponse<IosCert> queryIosCert(IosCertRequest request){
		request.setHttpMethod(HttpMethodName.POST);
		request.setMethodName("query_app_ioscert");
		
		PushHttpRequest httpRequest = createHttpRequest(request);
		return this.pushHttpClient.execute(httpRequest, new QueryIosCertHandler());
	}
	
	private void setCert(IosCert cert,PushHttpRequest httpRequest){
		httpRequest.addParameter("name", cert.getName());
		httpRequest.addParameter("description", cert.getDescription());
		httpRequest.addParameter("release_cert", PushUtil.toBase64(cert.getReleaseCert()));
		httpRequest.addParameter("dev_cert", PushUtil.toBase64(cert.getDevCert()));
	}
	
	private PushHttpRequest createHttpRequest(BaiduPushRequest request) {
		PushHttpRequest httpRequest = new DefaultPushHttpRequest(request);
		
		httpRequest.addParameter("method", request.getMethodName());
		httpRequest.addParameter("apikey", credentials.getAccessKey());
		httpRequest.addParameter("timestamp", System.currentTimeMillis() / 1000);
		
		httpRequest.setHttpMethod(request.getHttpMethod());
		
		setDefaultParam(request, httpRequest);
		
		return httpRequest;
	}
	
	private void setDefaultParam(BaiduPushRequest request,PushHttpRequest httpRequest){
		String channelId = request.getChannelId();
		if(StringUtils.isNotBlank(channelId)){
			httpRequest.addParameter("channel_id", channelId);
		}
		
		List<DeviceType> deviceTypes = request.getDeviceTypes();
		String deviceTypeStr = PushUtil.generateDeviceType(deviceTypes);
		if(StringUtils.isNotBlank(deviceTypeStr)){
			httpRequest.addParameter("device_type", deviceTypeStr);
		}
		

		int expires = request.getExpires();
		if(expires != QueryBindRequest.DEFAULT_EXPIRES){
			httpRequest.addParameter("expires", expires);
		}
		
		if(request instanceof PagableRequest){
			PagableRequest pagableReq = (PagableRequest)request;
			int start = pagableReq.getStart();
			if(start != QueryBindRequest.DEFAULT_START){
				httpRequest.addParameter("start", start);
			}
			
			int limit = pagableReq.getLimit();
			if(limit != QueryBindRequest.DEFAULT_LIMIT){
				httpRequest.addParameter("limit", limit);
			}
		}
	}
}
