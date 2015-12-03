package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class ScreenResponse extends StatusInfo {
	private ScreenResult data;

	public ScreenResponse() {
		super();
	}

	public ScreenResult getData() {
		return data;
	}

	public void setData(ScreenResult data) {
		this.data = data;
	}

	
}
