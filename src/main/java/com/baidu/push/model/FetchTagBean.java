package com.baidu.push.model;

import java.util.List;

/**
 * 获取tag的返回结果对象
 * @author liyazhou@baidu.com
 *
 */
public class FetchTagBean {
	/**
	 * 系统返回的消息总数。
	 */
	private int totalNum;
	/**
	 * 本次查询绑定数量。
	 */
	private int count;
	/**
	 * 标签详情信息
	 */
	private List<Tag> tags;
	
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "FetchTagBean [totalNum=" + totalNum + ", count=" + count
				+ ", tags=" + tags + "]";
	}
}
