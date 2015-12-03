package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class UpdateCarsListResponse extends StatusInfo {

	private GetCarsListResponse2 data = null;

	public GetCarsListResponse2 getData() {
		return data;
	}

	public void setData(GetCarsListResponse2 data) {
		this.data = data;
	}

}
