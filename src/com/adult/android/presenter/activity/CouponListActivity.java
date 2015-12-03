package com.adult.android.presenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.adult.android.R;
import com.adult.android.presenter.fragment.CouponListFragment;
public class CouponListActivity extends BaseActivity implements OnClickListener {

	public static final String USEABLE = "FragmentUseable";

	public static final String OUTTIME = "FragmentOuttime";

	public static final String EXTRA_COMMUNITY_ID = "extra_community_id";

	private View viewUseable, viewOuttime;

	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowTitleBar(true);
		setActivityTitle("我的券包");
		initView();
	}

	/** 初始化 */
	private void initView() {
		findViewById(R.id.coupon_list_tab_useable).setOnClickListener(this);
		findViewById(R.id.coupon_list_tab_out_time).setOnClickListener(this);

		viewUseable = (View) findViewById(R.id.coupon_list_tab_view_useable);
		viewOuttime = (View) findViewById(R.id.coupon_list_tab_view_out_time);
		mFragmentManager = getSupportFragmentManager();
		switchTab(USEABLE);
	}

	/** 切换页面 */
	private void switchTab(String tag) {
		switchView(tag);
		Fragment fragment = mFragmentManager.findFragmentByTag(tag);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		if (null == fragment) {
			Bundle data = new Bundle();
			fragment = new CouponListFragment();
			if (USEABLE.equals(tag)) {
				data.putString("status", "1");
			} else {
				data.putString("status", "2");
			}
			fragment.setArguments(data);
			mFragmentTransaction.add(R.id.coupon_list_content, fragment, tag);
		} else {
			mFragmentTransaction.show(fragment);
		}
		mFragmentTransaction.commit();
	}

	/** 显示/隐藏背景条 */
	private void switchView(String tag) {
		viewUseable.setVisibility(View.INVISIBLE);
		viewOuttime.setVisibility(View.INVISIBLE);
		if (USEABLE.equals(tag)) {
			viewUseable.setVisibility(View.VISIBLE);
			return;
		}
		if (OUTTIME.equals(tag)) {
			viewOuttime.setVisibility(View.VISIBLE);
			return;
		}
	}

	/** 隐藏Fragment */
	private void hideFragmet(String tag) {
		Fragment fragment = mFragmentManager.findFragmentByTag(tag);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		if (null != fragment && !fragment.isHidden()) {
			mFragmentTransaction.hide(fragment);
			mFragmentTransaction.commit();
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.coupon_list_tab_useable:
			switchTab(USEABLE);
			hideFragmet(OUTTIME);
			break;
		case R.id.coupon_list_tab_out_time:
			switchTab(OUTTIME);
			hideFragmet(USEABLE);
			break;
		default:
			break;
		}

	}
}
