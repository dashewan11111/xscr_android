package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class CommunityResponse extends StatusInfo {

	private CommunityResponse2 data = null;

	public CommunityResponse() {
		super();
	}

	public CommunityResponse2 getData() {
		return data;
	}

	public void setData(CommunityResponse2 data) {
		this.data = data;
	}
}
