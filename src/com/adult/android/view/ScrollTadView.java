package com.adult.android.view;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.adult.android.R;
import com.adult.android.presenter.fragment.ScrollFragment;
import com.adult.android.presenter.fragment.main.tab.adapter.ScrollTabViewPagerAdapter;

public class ScrollTadView extends LinearLayout {

	private LinearLayout llytTabs;

	private MyViewPager myViewPager;

	public List<ScrollFragment> tabs;

	private Context context;

	private boolean canScroll = false;

	public ScrollTadView(Context context) {
		super(context);
	}

	public ScrollTadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_scrolltab, this);

		llytTabs = (LinearLayout) findViewById(R.id.scrolltab_llyt_tabs);
		myViewPager = (MyViewPager) findViewById(R.id.scrolltab_viewPager);
		myViewPager.setCanScroll(isCanScroll());
	}

	public void initViewPager(FragmentManager fragmentManager) {
		ScrollTabViewPagerAdapter adapter = new ScrollTabViewPagerAdapter(
				context, fragmentManager, myViewPager, tabs, llytTabs);
		adapter.setOnExtraPageChangeListener(new ScrollTabViewPagerAdapter.OnExtraPageChangeListener() {
			@Override
			public void onExtraPageSelected(int i) {
				// Log.e("onExtraPageSelected", "" + i);
			}
		});
		
		llytTabs.getChildAt(0).setBackgroundColor(
				context.getResources().getColor(R.color.white));
		LinearLayout view = (LinearLayout) llytTabs.getChildAt(0);
		Button btn = (Button) view.getChildAt(0).findViewById(R.id.scrollview_button_button);
		btn.setTextColor(context.getResources().getColor(R.color.common_purple));
		getFoucues(btn);
	}

	public void setTabs(Context context, List<ScrollFragment> tabs) {
		this.context = context;
		this.tabs = tabs;
		if (null != tabs && 0 < tabs.size()) {
			for (int i = 0; i < tabs.size(); i++) {
				View viewButton = LayoutInflater.from(context).inflate(
						R.layout.view_scrollview_button, null);
				viewButton.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
						1.0f));
				Button button = (Button) viewButton
						.findViewById(R.id.scrollview_button_button);
				button.setTextColor(Color.BLACK);
				button.setText(tabs.get(i).getTabName());
				button.setOnClickListener(new OnTabClickListener(i));
				llytTabs.addView(viewButton);
			}
		}
	}

	public boolean isCanScroll() {
		return canScroll;
	}

	public void setCanScroll(boolean canScroll) {
		this.canScroll = canScroll;
	}

	class OnTabClickListener implements OnClickListener {

		private int index = 0;

		public OnTabClickListener(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View arg0) {
			myViewPager.setCurrentItem(index);
		}
	}

	public MyViewPager getMyViewPager() {
		return myViewPager;
	}

	public void setMyViewPager(MyViewPager myViewPager) {
		this.myViewPager = myViewPager;
	}
	
	/**
	 * 获取焦点
	 * 
	 * @param view
	 */
	public static void getFoucues(View view) {
		if (null != view) {
			view.setFocusable(true);
			view.setFocusableInTouchMode(true);
			view.requestFocus();
			view.requestFocusFromTouch();
		}
	}
}
