package com.adult.android.entity;

import java.util.List;

public class OrderListInfo extends BaseEntity {
	private List<Order> result;
	private int totalCount;
	private int totalPage;

	public OrderListInfo() {
		super();
	}

	public List<Order> getResult() {
		return result;
	}

	public void setResult(List<Order> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
