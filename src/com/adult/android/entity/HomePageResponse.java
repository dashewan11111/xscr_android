package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class HomePageResponse extends StatusInfo {

	private HomePageDto data;

	public HomePageDto getData() {
		return data;
	}

	public void setData(HomePageDto data) {
		this.data = data;
	}

}
