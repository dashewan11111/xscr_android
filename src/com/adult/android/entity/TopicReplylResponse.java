package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class TopicReplylResponse extends StatusInfo {

	private String data = null;

	public TopicReplylResponse() {
		super();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
