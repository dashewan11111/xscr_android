package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class ProductCommentsResponse extends StatusInfo {
	private ProductCommentsInfo data;

	public ProductCommentsInfo getData() {
		return data;
	}

	public void setData(ProductCommentsInfo data) {
		this.data = data;
	}

	public ProductCommentsResponse() {
		super();
	}
}
