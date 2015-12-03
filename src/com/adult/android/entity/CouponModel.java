package com.adult.android.entity;

import java.math.BigDecimal;

/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年4月9日
 * 
 * @descripte
 * 
 *            优惠券 信息 封装
 */
public class CouponModel extends BaseEntity {
	private long couponstockid;

	private long ruleid;

	private Double price;

	private BigDecimal orderLimitPrice;

	private int couponType;

	private long startTime;

	private long endTime;

	private int useType;

	private String[] limitName;

	private String ruleId;
	private String categoryId;
	private String skuId;
	private String brandId;
	private String ratePrice;
	private String userid;
	private String index;
	private String fullCutPrice;
	private String sumPrice;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getRatePrice() {
		return ratePrice;
	}

	public void setRatePrice(String ratePrice) {
		this.ratePrice = ratePrice;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getFullCutPrice() {
		return fullCutPrice;
	}

	public void setFullCutPrice(String fullCutPrice) {
		this.fullCutPrice = fullCutPrice;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public long getRuleid() {
		return ruleid;
	}

	public void setRuleid(long ruleid) {
		this.ruleid = ruleid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BigDecimal getOrderLimitPrice() {
		return orderLimitPrice;
	}

	public void setOrderLimitPrice(BigDecimal orderLimitPrice) {
		this.orderLimitPrice = orderLimitPrice;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String[] getLimitName() {
		return limitName;
	}

	public void setLimitName(String[] limitName) {
		this.limitName = limitName;
	}

	public long getCouponstockid() {
		return couponstockid;
	}

	public void setCouponstockid(long couponstockid) {
		this.couponstockid = couponstockid;
	}

	public int getCouponType() {
		return couponType;
	}

	public void setCouponType(int couponType) {
		this.couponType = couponType;
	}

	public int getUseType() {
		return useType;
	}

	public void setUseType(int useType) {
		this.useType = useType;
	}

}
