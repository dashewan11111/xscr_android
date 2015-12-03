package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class ProductListResponse extends StatusInfo {
	private ProductListInfo data;

	public ProductListResponse() {
		super();
	}

	public ProductListInfo getData() {
		return data;
	}

	public void setData(ProductListInfo data) {
		this.data = data;
	}
}
