package com.adult.android.presenter.activity;

import android.os.Bundle;

public class UserProfile extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("个人资料");
	}
}
