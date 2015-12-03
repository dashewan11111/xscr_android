package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class GetCarsListResponse extends StatusInfo {

	private CartDto2 data = null;

	public CartDto2 getData() {
		return data;
	}

	public void setData(CartDto2 data) {
		this.data = data;
	}

}
