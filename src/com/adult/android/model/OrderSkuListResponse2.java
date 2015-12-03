package com.adult.android.model;

import java.util.List;

import com.adult.android.entity.BaseEntity;

public class OrderSkuListResponse2 extends BaseEntity {

	private List<OrderSku> list;

	public List<OrderSku> getList() {
		return list;
	}

	public void setList(List<OrderSku> list) {
		this.list = list;
	}
}
