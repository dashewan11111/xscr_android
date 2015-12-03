package com.adult.android.entity;

/**
 * @ClassName: CommentOrderInfo
 * @Description:
 * @author
 * @date 2015年4月1日 下午1:06:43
 * 
 */
public class CommentOrderInfo extends BaseEntity {
	private String orderId;
	private long buyDate;
	private String pid;
	private int skuTotalNum;

	public CommentOrderInfo() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(long buyDate) {
		this.buyDate = buyDate;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getSkuTotalNum() {
		return skuTotalNum;
	}

	public void setSkuTotalNum(int skuTotalNum) {
		this.skuTotalNum = skuTotalNum;
	}
}
