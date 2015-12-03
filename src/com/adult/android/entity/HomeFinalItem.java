package com.adult.android.entity;

import java.io.Serializable;

public class HomeFinalItem extends BaseEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2577355760807531621L;

	/**
	 * 首页展示信息
	 */
	private String content;

	/**
	 * 目标地址信息
	 */
	private String imgUrl;

	/**
	 * 目标类型 1，url，2，商品ID，3，类目id，4，关键字，5，专题id
	 */
	private Integer type;

	private Integer jumpId;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CustomerContentDto [content=" + content + ", targetUrl="
				+ imgUrl + ", type=" + type + "]";
	}

	public Integer getJumpId() {
		return jumpId;
	}

	public void setJumpId(Integer jumpId) {
		this.jumpId = jumpId;
	}

}
