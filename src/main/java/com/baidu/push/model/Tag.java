package com.baidu.push.model;

/**
 * 代表标签实体
 * @author liyazhou@baidu.com
 *
 */
public class Tag {
	/**
	 * 标签id
	 */
	private String tid;
	/**
	 * 标签名称
	 */
	private String name;
	/**
	 * 标签信息
	 */
	private String info;
	/**
	 * 标签类型
	 */
	private int type;
	/**
	 * 标签创建时间
	 */
	private long createTime;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", name=" + name + ", info=" + info
				+ ", type=" + type + ", createTime=" + createTime + "]";
	}
}
