package com.adult.android.entity;

import java.util.List;

public class ProductForCart extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7400510574446723598L;

	private String mainActivityId;

	private String mainActivityType;

	private String title;

	private List<SkuForCart> skuList;

	private String sumPrice;

	private String sumDomesticPrice;

	private String discountPrice;

	private String islock;

	private ProductRule proRule;

	private boolean isChecked = true;

	public String getMainActivityId() {
		return mainActivityId;
	}

	public void setMainActivityId(String mainActivityId) {
		this.mainActivityId = mainActivityId;
	}

	public String getMainActivityType() {
		return mainActivityType;
	}

	public void setMainActivityType(String mainActivityType) {
		this.mainActivityType = mainActivityType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SkuForCart> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<SkuForCart> skuList) {
		this.skuList = skuList;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getSumDomesticPrice() {
		return sumDomesticPrice;
	}

	public void setSumDomesticPrice(String sumDomesticPrice) {
		this.sumDomesticPrice = sumDomesticPrice;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

	public ProductRule getProRule() {
		return proRule;
	}

	public void setProRule(ProductRule proRule) {
		this.proRule = proRule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
