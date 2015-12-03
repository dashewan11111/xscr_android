package com.adult.android.entity;

import java.util.List;

public class PromotionDto extends BaseEntity {

	private String skuId;
	/** 服务器最新时间 **/
	private String newDate;
	/** 满减 **/
	private List<FullOrBuyGiftsInfo> fullReductions;
	/** 直降 **/
	private List<FullOrBuyGiftsInfo> priceDowns;
	/** 满赠 **/
	private List<FullOrBuyGiftsInfo> withIncreasings;
	/** 买赠 **/
	private List<FullOrBuyGiftsInfo> Buys;
	/** 满返 **/
	private List<FullOrBuyGiftsInfo> Fullbacks;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public List<FullOrBuyGiftsInfo> getFullReductions() {
		return fullReductions;
	}

	public void setFullReductions(List<FullOrBuyGiftsInfo> fullReductions) {
		this.fullReductions = fullReductions;
	}

	public List<FullOrBuyGiftsInfo> getPriceDowns() {
		return priceDowns;
	}

	public void setPriceDowns(List<FullOrBuyGiftsInfo> priceDowns) {
		this.priceDowns = priceDowns;
	}

	public List<FullOrBuyGiftsInfo> getWithIncreasings() {
		return withIncreasings;
	}

	public void setWithIncreasings(List<FullOrBuyGiftsInfo> withIncreasings) {
		this.withIncreasings = withIncreasings;
	}

	public List<FullOrBuyGiftsInfo> getBuys() {
		return Buys;
	}

	public void setBuys(List<FullOrBuyGiftsInfo> buys) {
		Buys = buys;
	}

	public List<FullOrBuyGiftsInfo> getFullbacks() {
		return Fullbacks;
	}

	public void setFullbacks(List<FullOrBuyGiftsInfo> fullbacks) {
		Fullbacks = fullbacks;
	}

	@Override
	public String toString() {
		return "PromotionDto{" + "skuId='" + skuId + '\'' + ", newDate='"
				+ newDate + '\'' + ", fullReductions=" + fullReductions
				+ ", priceDowns=" + priceDowns + ", withIncreasings="
				+ withIncreasings + ", Buys=" + Buys + ", Fullbacks="
				+ Fullbacks + '}';
	}
}
