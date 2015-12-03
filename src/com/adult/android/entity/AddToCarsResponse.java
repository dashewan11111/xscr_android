package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class AddToCarsResponse extends StatusInfo {

	private AddToCarsmessage data = null;

	public AddToCarsResponse() {
		super();
	}

	public AddToCarsmessage getData() {
		return data;
	}

	public void setData(AddToCarsmessage data) {
		this.data = data;
	}

}
