package com.adult.android.entity;

import java.io.Serializable;

public class OrderPromoteRelationDTO extends OrderPromoteRelation
		implements
			Serializable {
	private static final long serialVersionUID = 1L;

	private Long skuId;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public OrderPromoteRelationDTO() {

	}

	public OrderPromoteRelationDTO(OrderPromoteRelation orderPromoteRelation) {
		this.setId(orderPromoteRelation.getId());
		this.setActiveCouponId(orderPromoteRelation.getActiveCouponId());
		this.setOrderId(orderPromoteRelation.getOrderId());
		this.setType(orderPromoteRelation.getType());
		this.setUsePrice(orderPromoteRelation.getUsePrice());
	}

}