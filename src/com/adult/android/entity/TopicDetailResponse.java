package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class TopicDetailResponse extends StatusInfo {

	private TopicDetailResponse2 data = null;

	public TopicDetailResponse() {
		super();
	}

	public TopicDetailResponse2 getData() {
		return data;
	}

	public void setData(TopicDetailResponse2 data) {
		this.data = data;
	}
}
