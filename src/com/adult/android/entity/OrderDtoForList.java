package com.adult.android.entity;

import java.util.List;

public class OrderDtoForList extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8267400560434327325L;

	private String orderId;// 订单号

	private String userId;// 订单创建者

	private String createTime;// 订单创建时间

	private int status;// 订单状态

	private String imgUrl;// 商品图标

	private int productCount;

	private int skuCount;// 购买数量

	private String orderAmount;// 订单金额

	private String payWay;// 支付方式

	private String message;// 备注

	private List<Sku2> skuList;// 商品列表

	private String productAmount;// 商品总价

	private String transferAmount;// 邮费

	private String couponAmount;// 优惠金额

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Sku2> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku2> skuList) {
		this.skuList = skuList;
	}

	public String getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	public String getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getSkuCount() {
		return skuCount;
	}

	public void setSkuCount(int skuCount) {
		this.skuCount = skuCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

}
