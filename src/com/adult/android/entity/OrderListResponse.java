package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class OrderListResponse extends StatusInfo {

	private OrderListInfo data;

	public OrderListResponse() {
		super();
	}

	public OrderListInfo getData() {
		return data;
	}

	public void setData(OrderListInfo data) {
		this.data = data;
	}
}
