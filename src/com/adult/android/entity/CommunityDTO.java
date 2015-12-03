package com.adult.android.entity;

public class CommunityDTO extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7170929590592182844L;

	private String communityId;

	private String name;

	private String description;

	private String imgUrl;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
