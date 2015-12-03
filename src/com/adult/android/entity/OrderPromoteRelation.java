package com.adult.android.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderPromoteRelation implements Serializable {

	private static final long serialVersionUID = 7833108712653688667L;

	private Long id;

	private Short type;// 类型:1优惠券、2活动

	private Long orderId;// 订单号

	private Long activeCouponId;// 券号、活动号

	private BigDecimal usePrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getActiveCouponId() {
		return activeCouponId;
	}

	public void setActiveCouponId(Long activeCouponId) {
		this.activeCouponId = activeCouponId;
	}

	public BigDecimal getUsePrice() {
		return usePrice;
	}

	public void setUsePrice(BigDecimal usePrice) {
		this.usePrice = usePrice;
	}
}