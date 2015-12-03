package com.adult.android.entity;

import java.math.BigDecimal;

/**
 * @ClassName: ShipItemDto
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月24日 上午10:30:07
 * 
 */
public class ShipItemDto extends BaseEntity {

	private String id;
	private String shipId;
	private String skuId;
	private String pid;
	private int qty;
	private long createTime;
	private long lastEditTime;
	private String skuName;
	private String pName;
	private String productUrl;
	private BigDecimal pirce;

	public ShipItemDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public BigDecimal getPirce() {
		return pirce;
	}

	public void setPirce(BigDecimal pirce) {
		this.pirce = pirce;
	}
}
