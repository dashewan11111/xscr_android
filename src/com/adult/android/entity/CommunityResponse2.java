package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class CommunityResponse2 extends StatusInfo {

	private List<CommunityDTO> communityList = null;

	public CommunityResponse2() {
		super();
	}

	public List<CommunityDTO> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(List<CommunityDTO> communityList) {
		this.communityList = communityList;
	}

}
