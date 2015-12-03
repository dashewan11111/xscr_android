package com.adult.android.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuyujun 2015-4-9 下午3:44:39
 */
public class ResultCode implements Serializable {

	private static final long serialVersionUID = 1L;

	// 01 商品下架 02 库存不足
	private String code;

	private Long pid;

	private Long skuId;

	private Integer qty;

	private String skuName;

	private String pName;

	private String supplyType;

	private BigDecimal price;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
