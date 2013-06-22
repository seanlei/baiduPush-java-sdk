package com.baidu.push.model;

import java.util.List;

/**
 * 删除消息的返回结果实体，里面包含成功的条数和删除状态
 * @author liyazhou@baidu.com
 *
 */
public class DeleteMessageResponseBean {
	private int successCount;
	private List<MessageOperationDetail> statusList;
	
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public List<MessageOperationDetail> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<MessageOperationDetail> statusList) {
		this.statusList = statusList;
	}
	@Override
	public String toString() {
		return "DeleteMessageResponseBean [successCount=" + successCount
				+ ", statusList=" + statusList + "]";
	}
	
	
}
