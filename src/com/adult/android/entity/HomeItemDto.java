package com.adult.android.entity;

import java.io.Serializable;

public class HomeItemDto extends BaseEntity implements Serializable {

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
	 * 目标类型 1 单品页 2 列表页 3 详情页
	 */
	private Integer type;

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

}
