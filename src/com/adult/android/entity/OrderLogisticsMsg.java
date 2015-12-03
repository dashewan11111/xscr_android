package com.adult.android.entity;

/**
 * @ClassName: OrderLogisticsMsg
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月24日 上午10:25:04
 * 
 */
public class OrderLogisticsMsg extends BaseEntity {
	private String msg;
	private long createTime;
	private String createBy;

	public OrderLogisticsMsg() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
