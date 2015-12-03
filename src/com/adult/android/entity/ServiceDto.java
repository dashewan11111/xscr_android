package com.adult.android.entity;

public class ServiceDto extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4263709998685674826L;

	private String imgUrl;

	private String type;

	private String content;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
