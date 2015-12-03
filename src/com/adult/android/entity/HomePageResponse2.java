package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class HomePageResponse2 extends StatusInfo {

	private HomePageDto2 data;

	public HomePageDto2 getData() {
		return data;
	}

	public void setData(HomePageDto2 data) {
		this.data = data;
	}

}
