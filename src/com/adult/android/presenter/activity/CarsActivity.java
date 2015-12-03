package com.adult.android.presenter.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.adult.android.R;
import com.adult.android.presenter.fragment.main.tab.CarsFragment2;

/**
 * Created by Administrator on 2015/6/29.
 */
public class CarsActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowTitleBar(false);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("购物车");
		FragmentManager fragmentManager = getSupportFragmentManager();
		CarsFragment2 carsFragment = new CarsFragment2();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.fragment_container, carsFragment);
		transaction.commit();
	}
}
