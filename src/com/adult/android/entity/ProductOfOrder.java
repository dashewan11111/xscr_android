package com.adult.android.entity;

import java.math.BigDecimal;

import android.text.TextUtils;

import com.adult.android.model.constants.ServiceUrlConstants;

/**
 * @ClassName: ProductOfOrder
 * @Description
 * @author JingYuchuan
 * @date 2015年3月27日 下午4:08:24
 * 
 */
public class ProductOfOrder extends BaseEntity {
	private String pid;
	private int count;
	private String imgUrl;
	private String pName;
	private BigDecimal price;
	private String productName;

	public ProductOfOrder() {
		super();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
