package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class VersionInfoResponse extends StatusInfo{
	private VersionInfo data;

	public VersionInfo getData() {
		return data;
	}

	public void setData(VersionInfo data) {
		this.data = data;
	}
}
