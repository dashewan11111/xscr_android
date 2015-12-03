package com.adult.android.entity;

import java.util.List;

public class CalculateCartResponse2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8275357587470992478L;

	private AddressDto defaultAddress;

	private String skuIds;

	private String totalAmount;

	private String productAmount;

	private String transAmount;

	private String promotionAmount;

	private String availableCouponCount;

	private List<CouponDto> enableUse;

	private List<CouponDto> unenableUse;

	public AddressDto getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(AddressDto defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(String promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	public String getAvailableCouponCount() {
		return availableCouponCount;
	}

	public void setAvailableCouponCount(String availableCouponCount) {
		this.availableCouponCount = availableCouponCount;
	}

	public String getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String skuIds) {
		this.skuIds = skuIds;
	}

	public List<CouponDto> getEnableUse() {
		return enableUse;
	}

	public void setEnableUse(List<CouponDto> enableUse) {
		this.enableUse = enableUse;
	}

	public List<CouponDto> getUnenableUse() {
		return unenableUse;
	}

	public void setUnenableUse(List<CouponDto> unenableUse) {
		this.unenableUse = unenableUse;
	}

}
