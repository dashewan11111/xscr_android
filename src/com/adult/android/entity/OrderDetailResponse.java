package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class OrderDetailResponse extends StatusInfo {
	private OrderDetailInfo data;

	public OrderDetailResponse() {
		super();
	}

	public OrderDetailInfo getData() {
		return data;
	}

	public void setData(OrderDetailInfo data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "OrderDetailResponse [data=" + data + "]";
	}

}
