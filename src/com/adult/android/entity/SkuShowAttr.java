package com.adult.android.entity;

import java.math.BigDecimal;

public class SkuShowAttr extends BaseEntity {
	private String productId;
	private String skuId;
	private String attrValIdV;
	private String skuNameCn;
	private String skuSellCount;
	private int skuQty;
	private BigDecimal unitPrice;
	private BigDecimal domesticPrice;
	private String foreignPrice;
	private String tar;
	private String notTar;

	public SkuShowAttr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getAttrValIdV() {
		return attrValIdV;
	}

	public void setAttrValId(String attrValIdV) {
		this.attrValIdV = attrValIdV;
	}

	public String getSkuNameCn() {
		return skuNameCn;
	}

	public void setSkuNameCn(String skuNameCn) {
		this.skuNameCn = skuNameCn;
	}

	public String getSkuSellCount() {
		return skuSellCount;
	}

	public void setSkuSellCount(String skuSellCount) {
		this.skuSellCount = skuSellCount;
	}

	public int getSkuQty() {
		return skuQty;
	}

	public void setSkuQty(int skuQty) {
		this.skuQty = skuQty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDomesticPrice() {
		return domesticPrice;
	}

	public void setDomesticPrice(BigDecimal domesticPrice) {
		this.domesticPrice = domesticPrice;
	}

	public String getForeignPrice() {
		return foreignPrice;
	}

	public void setForeignPrice(String foreignPrice) {
		this.foreignPrice = foreignPrice;
	}

	public String getTar() {
		return tar;
	}

	public void setTar(String tar) {
		this.tar = tar;
	}

	public String getNotTar() {
		return notTar;
	}

	public void setNotTar(String notTar) {
		this.notTar = notTar;
	}

}
