package com.adult.android.entity;

import java.util.List;

public class Product2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7899784392118989825L;

	private String productId; // 商品id

	private String name; // 商品名称

	private String price;// 商品描述

	private List<String> imgList;// 商品展示的默认图片

	private List<SkuForCart> skuList; // skuList

	private int salesVolume;// 销量

	private String desc; // Sku描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<SkuForCart> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<SkuForCart> skuList) {
		this.skuList = skuList;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

}
