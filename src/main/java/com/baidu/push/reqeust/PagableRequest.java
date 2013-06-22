package com.baidu.push.reqeust;

/**
 * @author liyazhou@baidu.com
 *
 */
public class PagableRequest extends BaiduPushRequest{
	/**
	 * 查询起始页码，默认为0。
	 */
	private int start = DEFAULT_START;
	/**
	 * 一次查询条数，默认为10。
	 */
	private int limit = DEFAULT_LIMIT;
	
	public PagableRequest() {}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
