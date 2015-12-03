package com.adult.android.entity;

import java.util.List;

public class NewOrderListResponse2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2373560660512359227L;

	private List<OrderDtoForList> orderList;

	public List<OrderDtoForList> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDtoForList> orderList) {
		this.orderList = orderList;
	}
}
