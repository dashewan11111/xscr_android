package com.adult.android.presenter.fragment;

import android.support.v4.app.Fragment;

public class ScrollFragment {

	private String tabName;

	private Fragment tabContent;

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Fragment getTabContent() {
		return tabContent;
	}

	public void setTabContent(Fragment tabContent) {
		this.tabContent = tabContent;
	}

}
