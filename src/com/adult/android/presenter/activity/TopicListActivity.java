package com.adult.android.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.adult.android.R;
import com.adult.android.presenter.fragment.TopicListFragment;

public class TopicListActivity extends BaseActivity implements OnClickListener {

	public static final String Tag = "CommunityFragment";

	public static final String ALL = "FragmentAll";

	public static final String NEW = "FragmentNew";

	public static final String ESSENCE = "FragmentEssence";

	public static final String EXTRA_COMMUNITY_ID = "extra_community_id";

	private View viewAll, viewNew, ViewEssence;

	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowTitleBar(true);
		setActivityTitle("帖子列表");
		setActivityTitleColor(getResources().getColor(R.color.white));
		setTitleBackground(R.drawable.common_top_bg);
		setRighButtonBackGround(R.drawable.community_post_topic);
		setRightButtonOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(TopicListActivity.this,
						PostTopicActivity.class);
				intent.putExtra("communityId",
						getIntent().getStringExtra(EXTRA_COMMUNITY_ID));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
			}
		});
		initView();
	}

	/** 初始化 */
	private void initView() {
		findViewById(R.id.community_tab_all).setOnClickListener(this);
		findViewById(R.id.community_tab_new).setOnClickListener(this);
		findViewById(R.id.community_tab_essence).setOnClickListener(this);
		viewAll = (View) findViewById(R.id.community_tab_view_all);
		viewNew = (View) findViewById(R.id.community_tab_view_new);
		ViewEssence = (View) findViewById(R.id.community_tab_view_essence);
		mFragmentManager = getSupportFragmentManager();
		switchTab(ALL);
	}

	/** 切换页面 */
	private void switchTab(String tag) {
		switchView(tag);
		Fragment fragment = mFragmentManager.findFragmentByTag(tag);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		if (null == fragment) {
			fragment = new TopicListFragment();
			Bundle data = new Bundle();
			data.putSerializable("community",
					getIntent().getSerializableExtra("community"));
			data.putString(EXTRA_COMMUNITY_ID,
					getIntent().getStringExtra(EXTRA_COMMUNITY_ID));

			if (ALL.equals(tag)) {
				data.putString("type", "1");
			} else if (NEW.equals(tag)) {
				data.putString("type", "2");
			} else {
				data.putString("type", "3");
			}
			fragment.setArguments(data);
			mFragmentTransaction.add(R.id.fragment_community_content, fragment,
					tag);
		} else {
			mFragmentTransaction.show(fragment);
		}
		mFragmentTransaction.commit();
	}

	/** 显示/隐藏背景条 */
	private void switchView(String tag) {
		viewAll.setVisibility(View.INVISIBLE);
		viewNew.setVisibility(View.INVISIBLE);
		ViewEssence.setVisibility(View.INVISIBLE);
		if (ALL.equals(tag)) {
			viewAll.setVisibility(View.VISIBLE);
			return;
		}

		if (NEW.equals(tag)) {
			viewNew.setVisibility(View.VISIBLE);
			return;
		}

		if (ESSENCE.equals(tag)) {
			ViewEssence.setVisibility(View.VISIBLE);
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
		case R.id.community_tab_all:
			switchTab(ALL);
			hideFragmet(NEW);
			hideFragmet(ESSENCE);
			break;
		case R.id.community_tab_new:
			switchTab(NEW);
			hideFragmet(ALL);
			hideFragmet(ESSENCE);
			break;
		case R.id.community_tab_essence:
			switchTab(ESSENCE);
			hideFragmet(NEW);
			hideFragmet(ALL);
			break;
		default:
			break;
		}

	}
}
