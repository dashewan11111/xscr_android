package com.adult.android.entity;

import java.math.BigDecimal;
import java.util.List;

public class ProductAtt extends BaseEntity {
	// 商品ID
	private String productId;

	private int prodType;
	// 商品名称
	private String b2cProductName;
	// 品牌ID 不展示
	private String brandId;
	// 原产国名称
	private String originplaceName;

	private String originplaceImage;
	// 类目ID 不展示
	private String catePubId;
	// 外币汇率 计算使用
	private BigDecimal tax;
	// 外币符号 $ ¥
	private String moneyUnitSymbols;
	// 外币ID 不展示
	private int moneyUnitId;
	// 售卖单位ID 不展示
	private String measureId;
	// 售卖单位值 （个，件，台）
	private String measureName;
	// 商品长描述URL
	private String b2cDescription;
	// 不展示
	private String hscode;
	// html字符串
	private String productAttrUrl;
	// 类目名称
	private String catePubName;

	private int tariff;

	private String buyAttrNameCn;

	private String buyAttrNameEn;

	private ShowProdAttr showProdAttr;

	// 属性ID 不展示
	private List<SkuShowAttr> skuShowList;

	// 货源种类
	/**
	 * 跨境购：10+ 直邮：海外直邮 11 备货：由国内保税区发货 12 一般贸易：0-9 国内现货（不是跨境购商品）1（默认值）
	 */
	private int supply = 1;

	public ProductAtt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProdType() {
		return prodType;
	}

	public void setProdType(int prodType) {
		this.prodType = prodType;
	}

	public String getB2cProductName() {
		return b2cProductName;
	}

	public void setB2cProductName(String b2cProductName) {
		this.b2cProductName = b2cProductName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getOriginplaceName() {
		return originplaceName;
	}

	public void setOriginplaceName(String originplaceName) {
		this.originplaceName = originplaceName;
	}

	public String getOriginplaceImage() {
		return originplaceImage;
	}

	public void setOriginplaceImage(String originplaceImage) {
		this.originplaceImage = originplaceImage;
	}

	public String getCatePubId() {
		return catePubId;
	}

	public void setCatePubId(String catePubId) {
		this.catePubId = catePubId;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getMoneyUnitSymbols() {
		return moneyUnitSymbols;
	}

	public void setMoneyUnitSymbols(String moneyUnitSymbols) {
		this.moneyUnitSymbols = moneyUnitSymbols;
	}

	public int getMoneyUnitId() {
		return moneyUnitId;
	}

	public void setMoneyUnitId(int moneyUnitId) {
		this.moneyUnitId = moneyUnitId;
	}

	public String getMeasureId() {
		return measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getB2cDescription() {
		return b2cDescription;
	}

	public void setB2cDescription(String b2cDescription) {
		this.b2cDescription = b2cDescription;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getProductAttrUrl() {
		return productAttrUrl;
	}

	public void setProductAttrUrl(String productAttrUrl) {
		this.productAttrUrl = productAttrUrl;
	}

	public String getCatePubName() {
		return catePubName;
	}

	public void setCatePubName(String catePubName) {
		this.catePubName = catePubName;
	}

	public int getTariff() {
		return tariff;
	}

	public void setTariff(int tariff) {
		this.tariff = tariff;
	}

	public String getBuyAttrNameCn() {
		return buyAttrNameCn;
	}

	public void setBuyAttrNameCn(String buyAttrNameCn) {
		this.buyAttrNameCn = buyAttrNameCn;
	}

	public String getBuyAttrNameEn() {
		return buyAttrNameEn;
	}

	public void setBuyAttrNameEn(String buyAttrNameEn) {
		this.buyAttrNameEn = buyAttrNameEn;
	}

	public ShowProdAttr getShowProdAttr() {
		return showProdAttr;
	}

	public void setShowProdAttr(ShowProdAttr showProdAttr) {
		this.showProdAttr = showProdAttr;
	}

	public List<SkuShowAttr> getSkuShowList() {
		return skuShowList;
	}

	public void setSkuShowList(List<SkuShowAttr> skuShowList) {
		this.skuShowList = skuShowList;
	}

	public int getSupply() {
		return supply;
	}

	public void setSupply(int supply) {
		this.supply = supply;
	}

}
