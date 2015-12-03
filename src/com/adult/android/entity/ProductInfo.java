package com.adult.android.entity;

import java.util.List;

public class ProductInfo extends BaseEntity {
	private int id;
	private int totalQty;
	private float orderPrice;
	private int status;
	private long createTime;
	private List<ProductObject> productList;

	public ProductInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<ProductObject> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductObject> productList) {
		this.productList = productList;
	}

}
