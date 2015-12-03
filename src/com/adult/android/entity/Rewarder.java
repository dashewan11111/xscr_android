package com.adult.android.entity;

public class Rewarder extends BaseEntity {

	private String userId;

	private String loginName;

	private String userImgUrl;

	private String vLevel;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserImgUrl() {
		return userImgUrl;
	}

	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	public String getvLevel() {
		return vLevel;
	}

	public void setvLevel(String vLevel) {
		this.vLevel = vLevel;
	}

}
