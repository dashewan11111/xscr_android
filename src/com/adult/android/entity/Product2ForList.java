package com.adult.android.entity;


public class Product2ForList extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7899784392118989825L;

	private String pid; // 商品id

	private String name; // 商品名称

	private String price;// 商品描述

	private String imgUrl;// 商品展示的默认图片

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

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
