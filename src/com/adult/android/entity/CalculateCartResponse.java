package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class CalculateCartResponse extends StatusInfo {

	private CalculateCartResponse2 data;

	public CalculateCartResponse2 getData() {
		return data;
	}

	public void setData(CalculateCartResponse2 data) {
		this.data = data;
	}

}
