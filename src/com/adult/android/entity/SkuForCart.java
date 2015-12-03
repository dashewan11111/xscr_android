package com.adult.android.entity;

import java.util.List;

public class SkuForCart extends Sku2 {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4246156516643470807L;

	private String categoryId;

	private String mainActivityId;

	private String ruleAlias;

	private String itemId;

	private String itemName;

	private String skuId;

	private String skuCode;

	private String skuName;

	private int qty;

	private String price;

	private String promotionPrice;

	private String marketPrice;

	private int stockQty;

	private String imgUrl;

	private int isSoldOut;

	private String description;

	private String subTotalPrice;

	private String discountedRuleId;

	private String skuMarketPrice;

	private List<ProductRule> proRuleList;

	private boolean isChecked = true;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMainActivityId() {
		return mainActivityId;
	}

	public void setMainActivityId(String mainActivityId) {
		this.mainActivityId = mainActivityId;
	}

	public String getRuleAlias() {
		return ruleAlias;
	}

	public void setRuleAlias(String ruleAlias) {
		this.ruleAlias = ruleAlias;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(int isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(String subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public String getDiscountedRuleId() {
		return discountedRuleId;
	}

	public void setDiscountedRuleId(String discountedRuleId) {
		this.discountedRuleId = discountedRuleId;
	}

	public String getSkuMarketPrice() {
		return skuMarketPrice;
	}

	public void setSkuMarketPrice(String skuMarketPrice) {
		this.skuMarketPrice = skuMarketPrice;
	}

	public List<ProductRule> getProRuleList() {
		return proRuleList;
	}

	public void setProRuleList(List<ProductRule> proRuleList) {
		this.proRuleList = proRuleList;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
