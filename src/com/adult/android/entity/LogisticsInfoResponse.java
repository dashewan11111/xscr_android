package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class LogisticsInfoResponse extends StatusInfo {
	private LogisticsInfo data;

	public LogisticsInfoResponse() {
		super();
	}

	public LogisticsInfo getData() {
		return data;
	}

	public void setData(LogisticsInfo data) {
		this.data = data;
	}
}
