package com.adult.android.model;

import com.adult.android.model.internet.bean.StatusInfo;

public class CommentResponse extends StatusInfo {

	private CommentResponse2 data;

	public CommentResponse2 getData() {
		return data;
	}

	public void setData(CommentResponse2 data) {
		this.data = data;
	}
}
