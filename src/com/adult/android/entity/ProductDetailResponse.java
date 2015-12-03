package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class ProductDetailResponse extends StatusInfo {

	private ProductDetailsDto data;

	public ProductDetailResponse() {
		super();
	}

	public ProductDetailsDto getData() {
		return data;
	}

	public void setData(ProductDetailsDto data) {
		this.data = data;
	}

}
