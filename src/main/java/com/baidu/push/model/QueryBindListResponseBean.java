package com.baidu.push.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询设备绑定关系的返回实体
 * @author liyazhou@baidu.com
 *
 */
public class QueryBindListResponseBean implements Serializable{
	
	private static final long serialVersionUID = 6029588215087717152L;
	
	/**
	 * 绑定数据总数
	 */
	private int totalNum;
	/**
	 * 本次查询绑定数量。
	 */
	private int amount; 
	
	/**
	 * 绑定明细
	 */
	private List<QueryBindListItem> bindItems = new ArrayList<QueryBindListItem>();
	
	public void addItem(QueryBindListItem item){
		bindItems.add(item);
	}
	
	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<QueryBindListItem> getBindItems() {
		return bindItems;
	}

	public void setBindItems(List<QueryBindListItem> bindItems) {
		this.bindItems = bindItems;
	}

	@Override
	public String toString() {
		return "QueryBindListResponseBean [totalNum=" + totalNum + ", amount="
				+ amount + ", bindItems=" + bindItems + "]";
	}
}
