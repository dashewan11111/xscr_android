package com.adult.android.entity;

import java.util.List;

public class HomePageDto2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4274692922741320603L;

	private List<HomeFinalItem> banner;

	private List<HomeFinalItem> guide;

	private List<HomeGroup> group;

	public List<HomeFinalItem> getBanner() {
		return banner;
	}

	public void setBanner(List<HomeFinalItem> banner) {
		this.banner = banner;
	}

	public List<HomeGroup> getGroup() {
		return group;
	}

	public void setGroup(List<HomeGroup> group) {
		this.group = group;
	}

	public List<HomeFinalItem> getGuide() {
		return guide;
	}

	public void setGuide(List<HomeFinalItem> guide) {
		this.guide = guide;
	}
}
