package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class NewOrderListResponse extends StatusInfo {

	private NewOrderListResponse2 data;

	public NewOrderListResponse() {
		super();
	}

	public NewOrderListResponse2 getData() {
		return data;
	}

	public void setData(NewOrderListResponse2 data) {
		this.data = data;
	}
}
