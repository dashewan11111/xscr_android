package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class TopicResponse extends StatusInfo {

	private TopicResponse2 data = null;

	public TopicResponse() {
		super();
	}

	public TopicResponse2 getData() {
		return data;
	}

	public void setData(TopicResponse2 data) {
		this.data = data;
	}
}
