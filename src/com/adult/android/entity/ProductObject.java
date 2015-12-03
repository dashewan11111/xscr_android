package com.adult.android.entity;

import java.math.BigDecimal;

import android.text.TextUtils;

import com.adult.android.model.constants.ServiceUrlConstants;

/**
 * @ClassName: ProductObject
 * @Description
 * @author JingYuchuan
 * @date 2015年3月27日 下午3:07:34
 * 
 */
public class ProductObject extends BaseEntity {
	private String pid;
	private String id;
	private String orderId;
	private String skuId;
	private String pName;
	private String skuNameCn;
	private String unit;
	private int skuQty;
	private BigDecimal price;
	private BigDecimal subtotalPirce;
	private BigDecimal taxRate;
	private BigDecimal tax;
	private BigDecimal discountPrice;
	private long createTime;
	private long lastEditTime;
	private String createBy;// 是否评价(值为comment就是已评论)
	private String lastEditBy;
	private String productId;
	private float weight;
	private float volume;
	private String imgUrl;
	private boolean freePostage;

	public ProductObject() {
		super();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getSkuNameCn() {
		return skuNameCn;
	}

	public void setSkuNameCn(String skuNameCn) {
		this.skuNameCn = skuNameCn;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getSkuQty() {
		return skuQty;
	}

	public void setSkuQty(int skuQty) {
		this.skuQty = skuQty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotalPirce() {
		return subtotalPirce;
	}

	public void setSubtotalPirce(BigDecimal subtotalPirce) {
		this.subtotalPirce = subtotalPirce;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(long lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public String getImgUrl() {
		if (!TextUtils.isEmpty(imgUrl) && !imgUrl.startsWith("http:")) {
			return ServiceUrlConstants.getImageHost() + imgUrl;
		} else {
			return imgUrl;
		}
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the freePostage
	 */
	public boolean isFreePostage() {
		return freePostage;
	}

	/**
	 * @param freePostage
	 *            the freePostage to set
	 */
	public void setFreePostage(boolean freePostage) {
		this.freePostage = freePostage;
	}
}
