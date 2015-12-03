package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class OrderProductsListResponse extends StatusInfo {
	private List<ProductObject> data;

	public OrderProductsListResponse() {
		super();
	}

	public List<ProductObject> getData() {
		return data;
	}

	public void setData(List<ProductObject> data) {
		this.data = data;
	}

}
