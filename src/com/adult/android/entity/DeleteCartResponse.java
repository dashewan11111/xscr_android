package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class DeleteCartResponse extends StatusInfo {

	private DeleteCartResponse2 data = null;

	public DeleteCartResponse() {
		super();
	}

	public DeleteCartResponse2 getData() {
		return data;
	}

	public void setData(DeleteCartResponse2 data) {
		this.data = data;
	}
}
