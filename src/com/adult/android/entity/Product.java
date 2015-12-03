package com.adult.android.entity;

import java.util.List;

/**
 * 商品类
 * 
 * @author Cool
 * 
 */
public class Product extends BaseEntity {
	// 商品ID
	private String productId;
	// 商品名称
	private String b2cProductName;
	// 品牌ID 不展示
	private String brandId;
	// 品牌中文名
	private String brandName;
	// 原产国ID 不展示
	private String originplaceId;
	// 原产国名称
	private String originplaceName;
	// 不展示
	private String hscode;
	// 类目ID 不展示
	private String catePubId;
	// 类目名称
	private String catePubName;
	// 外币汇率 计算使用
	private int tax;
	// 外币符号 $ ¥
	private String moneyUnitSymbols;
	// 外币ID 不展示
	private String moneyUnitId;
	// 售卖单位ID 不展示
	private String measureId;
	// 售卖单位值 （个，件，台）
	private String measureName;
	// 商品长描述URL
	private String b2cDescription;
	// 商品一般属性信息
	private List<ProdAttr> ProdAttrs;
	// 商品ID 不展示
	private ShowProdAttr showProdAttr;
	// 属性ID 不展示
	private List<SkuShowAttr> skuShowList;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getOriginplaceId() {
		return originplaceId;
	}

	public void setOriginplaceId(String originplaceId) {
		this.originplaceId = originplaceId;
	}

	public String getOriginplaceName() {
		return originplaceName;
	}

	public void setOriginplaceName(String originplaceName) {
		this.originplaceName = originplaceName;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getCatePubId() {
		return catePubId;
	}

	public void setCatePubId(String catePubId) {
		this.catePubId = catePubId;
	}

	public String getCatePubName() {
		return catePubName;
	}

	public void setCatePubName(String catePubName) {
		this.catePubName = catePubName;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
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

	public List<ProdAttr> getProdAttrs() {
		return ProdAttrs;
	}

	public void setProdAttrs(List<ProdAttr> ProdAttrs) {
		this.ProdAttrs = ProdAttrs;
	}

	public ShowProdAttr getShowProdAttr() {
		return showProdAttr;
	}

	public void setShowProdAttr(ShowProdAttr showProdAttr) {
		this.showProdAttr = showProdAttr;
	}

	public String getMoneyUnitSymbols() {
		return moneyUnitSymbols;
	}

	public void setMoneyUnitSymbols(String moneyUnitSymbols) {
		this.moneyUnitSymbols = moneyUnitSymbols;
	}

	public String getMoneyUnitId() {
		return moneyUnitId;
	}

	public void setMoneyUnitId(String moneyUnitId) {
		this.moneyUnitId = moneyUnitId;
	}

	public List<SkuShowAttr> getSkuShowList() {
		return skuShowList;
	}

	public void setSkuShowList(List<SkuShowAttr> skuShowList) {
		this.skuShowList = skuShowList;
	}

}
