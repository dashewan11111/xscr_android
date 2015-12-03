package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class ProductResponse extends StatusInfo {

	private ProductListDto data = null;

	public ProductResponse() {
		super();
	}

	public ProductListDto getData() {
		return data;
	}

	public void setData(ProductListDto data) {
		this.data = data;
	}
}
