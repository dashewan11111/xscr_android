package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

/**
 * Created by Administrator on 2015/7/2.
 */
public class OrderCount extends StatusInfo {

	private OrderCountInfo data;

	public OrderCountInfo getData() {
		return data;
	}

	public void setData(OrderCountInfo data) {
		this.data = data;
	}
}
