package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class AddToCartResponse extends StatusInfo {

	private AddToCartResponse2 data = null;

	public AddToCartResponse() {
		super();
	}

	public AddToCartResponse2 getData() {
		return data;
	}

	public void setData(AddToCartResponse2 data) {
		this.data = data;
	}
}
