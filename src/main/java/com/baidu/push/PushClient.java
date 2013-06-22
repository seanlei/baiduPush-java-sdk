package com.baidu.push;

import java.util.List;

import com.baidu.push.model.DeleteMessageResponseBean;
import com.baidu.push.model.DeviceType;
import com.baidu.push.model.Empty;
import com.baidu.push.model.FetchTagBean;
import com.baidu.push.model.IosCert;
import com.baidu.push.model.Message;
import com.baidu.push.model.QueryBindListResponseBean;
import com.baidu.push.reqeust.DeleteMsgRequest;
import com.baidu.push.reqeust.DeleteTagRequest;
import com.baidu.push.reqeust.FetchMessageRequest;
import com.baidu.push.reqeust.FetchMsgCountRequest;
import com.baidu.push.reqeust.FetchTagRequest;
import com.baidu.push.reqeust.IosCertRequest;
import com.baidu.push.reqeust.PushMessageRequest;
import com.baidu.push.reqeust.QueryBindRequest;
import com.baidu.push.reqeust.QueryDeviceTypeRequest;
import com.baidu.push.reqeust.QueryUserTagsRequest;
import com.baidu.push.reqeust.SetTagRequest;
import com.baidu.push.reqeust.VerifyBindRequest;
import com.baidu.push.response.PushResponse;

/**
 * 推送Facade接口
 * 
 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list">官方文档</a>
 * @author liyazhou@baidu.com
 *
 */
public interface PushClient {
	/**
	 * 查询设备、应用、用户与百度Channel的绑定关系。
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#query_bindlist">官方文档</a>
	 * @param request 查询绑定状态的请求对象
	 * @return 设备、用户、与channel直接的bind关系列表
	 */
	PushResponse<QueryBindListResponseBean> queryBind(QueryBindRequest request);

	/**
	 * 推送消息，该接口可用于推送单个人、一群人、所有人以及固定设备的使用场景。
	 * 目前iso只支持单播消息, 只支持notify，并且消息类型要严格符合apple的apns格式
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#push_msg">官方文档</a>
	 * @param request 推送消息请求
	 * @return
	 */
	PushResponse<Integer> pushMessage(PushMessageRequest request);

	/**
	 * 判断设备、应用、用户与Channel的绑定关系是否存在。
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * 
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#verify_bind">官方文档</a>
	 * @param request 查询绑定关系请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明绑定关系存在
	 */
	PushResponse<Empty> veryBind(VerifyBindRequest request);

	/**
	 * 查询离线消息。
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * 
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#fetch_msg">官方文档</a>
	 * @param request 查询离线消息请求
	 * @return 离线消息的一个集合
	 */
	PushResponse<List<Message>> fetchMessage(FetchMessageRequest request);

	/**
	 * 查询离线消息的个数。
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#fetch_msgcount">官方文档</a>
	 * @param request 查询离线消息数目请求
	 * @return 离线消息数目
	 */
	PushResponse<Integer> fetchMessageCount(FetchMsgCountRequest request);

	/**
	 * 删除离线消息。
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#delete_msg">官方文档</a>
	 * @param request 删除离线消息请求
	 * @return 删除离线消息成功的个数
	 */
	PushResponse<DeleteMessageResponseBean> deleteMessage(DeleteMsgRequest request);

	/**
	 * 服务器端设置用户标签。当该标签不存在时，服务端将会创建该标签。
	 * 特别地，当user_id被提交时，服务端将会完成用户和tag的绑定操作。
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#set_tag">官方文档</a>
	 * @param request 创建tag请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明绑定关系存在
	 */
	PushResponse<Empty> setTag(SetTagRequest request);

	/**
	 * 查询应用的所有标签
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#fetch_tag">官方文档</a>
	 * @param request 查询用户标签请求
	 * @return 标签列表
	 */
	PushResponse<FetchTagBean> fetchTag(FetchTagRequest request);
	
	/**
	 * 删除标签，其中tag参数是必填项
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#delete_tag">官方文档</a>
	 * @param request 删除标签请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明删除成功
	 */
	PushResponse<Empty> deleteTag(DeleteTagRequest request);

	/**
	 * 查询用户所属的标签列表。其中user_id是必填项
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#query_user_tags">官方文档</a>
	 * @param request 查询用户所属标签请求
	 * @return 和用户相关的标签列表
	 */
	PushResponse<FetchTagBean> queryUserTags(QueryUserTagsRequest request);

	/**
	 * 初始iso证书信息
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#init_app_ioscert">官方文档</a>
	 * @param request 初始证书信息的请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明初始化成功
	 */
	PushResponse<Empty> initIosCert(IosCertRequest request);
	
	/**
	 * 更新iso证书信息
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#update_app_ioscert">官方文档</a>
	 * @param request 更新证书信息的请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明初始化成功
	 */
	PushResponse<Empty> updateIosCert(IosCertRequest request);

	
	/**
	 * 查询iso证书信息
	 * 如果查询的绑定关系与channel_id无关，则不需要传递channel_id参数
	 * 这个请求IsoCert参数将会被忽略
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#query_app_ioscert">官方文档</a>
	 * @param request 查询证书信息的请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明初始化成功
	 */
	PushResponse<IosCert> queryIosCert(IosCertRequest request);

	/**
	 * 删除iso证书
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#delete_app_ioscert">官方文档</a>
	 * @param request 删除iso证书的请求
	 * @return 这个地方如果不抛出异常，正常返回request_id就表明初始化成功
	 */
	PushResponse<Empty> deleteIosCert(IosCertRequest request);

	/**
	 * 根据channel_id查询设备类型。
	 * 这个里面channel_id是必填项
	 * @see <a href="http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#query_device_type">官方文档</a>
	 * @param request 查询设备类型的请求
	 * @return 返回绑定的设备类型
	 */
	PushResponse<DeviceType> queryDeviceType(QueryDeviceTypeRequest request);

}
