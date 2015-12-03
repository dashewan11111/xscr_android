package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.adult.android.R;
import com.adult.android.presenter.fragment.ScrollFragment;
import com.adult.android.view.MyViewPager;

/**
 * 为ViewPager添加布局（Fragment），绑定和处理fragments和viewpager之间的逻辑关系
 * 
 */
public class ScrollTabViewPagerAdapter extends PagerAdapter implements
		ViewPager.OnPageChangeListener {
	private List<ScrollFragment> fragments; // 每个Fragment对应一个Page
	private FragmentManager fragmentManager;
	private MyViewPager viewPager; // viewPager对象
	private int currentPageIndex = 0; // 当前page索引（切换之前）

	private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加接口

	private LinearLayout llytTabs;

	private Context context;

	public ScrollTabViewPagerAdapter(Context context,
			FragmentManager fragmentManager, MyViewPager viewPager,
			List<ScrollFragment> fragments, LinearLayout llytTabs) {
		this.context = context;
		this.fragments = fragments;
		this.fragmentManager = fragmentManager;
		this.viewPager = viewPager;
		this.llytTabs = llytTabs;
		this.viewPager.setAdapter(this);
		this.viewPager.setOnPageChangeListener(this);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object o) {
		return view == o;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(fragments.get(position).getTabContent().getView()); // 移出viewpager两边之外的page布局
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = fragments.get(position).getTabContent();
		if (!fragment.isAdded()) { // 如果fragment还没有added
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(fragment, fragment.getClass().getSimpleName());
			ft.commit();
			/**
			 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
			 * 会在进程的主线程中，用异步的方式来执行。 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
			 * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
			 */
			fragmentManager.executePendingTransactions();
		}

		if (fragment.getView().getParent() == null) {
			container.addView(fragment.getView()); // 为viewpager增加布局
		}

		return fragment.getView();
	}

	/**
	 * 当前page索引（切换之前）
	 * 
	 * @return
	 */
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public OnExtraPageChangeListener getOnExtraPageChangeListener() {
		return onExtraPageChangeListener;
	}

	/**
	 * 设置页面切换额外功能监听器
	 * 
	 * @param onExtraPageChangeListener
	 */
	public void setOnExtraPageChangeListener(
			OnExtraPageChangeListener onExtraPageChangeListener) {
		this.onExtraPageChangeListener = onExtraPageChangeListener;
	}

	@Override
	public void onPageScrolled(int i, float v, int i2) {
		if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
			onExtraPageChangeListener.onExtraPageScrolled(i, v, i2);
		}
	}

	@Override
	public void onPageSelected(int i) {
		for (int a = 0; a < llytTabs.getChildCount(); a++) {
			if (i != a) {
				llytTabs.getChildAt(a).setBackgroundColor(
						context.getResources().getColor(R.color.common_bg));
				LinearLayout view = (LinearLayout) llytTabs.getChildAt(a);
				Button btn = (Button) view.getChildAt(0);
				btn.setTextColor(Color.BLACK);
			} else {
				llytTabs.getChildAt(a).setBackgroundColor(
						context.getResources().getColor(R.color.common_purple));
				LinearLayout view = (LinearLayout) llytTabs.getChildAt(a);
				Button btn = (Button) view.getChildAt(0);
				btn.setTextColor(context.getResources().getColor(
						R.color.common_purple));
			}
		}
		fragments.get(currentPageIndex).getTabContent().onPause(); // 调用切换前Fargment的onPause()
		// fragments.get(currentPageIndex).onStop(); // 调用切换前Fargment的onStop()
		if (fragments.get(i).getTabContent().isAdded()) {
			// fragments.get(i).onStart(); // 调用切换后Fargment的onStart()
			fragments.get(i).getTabContent().onResume(); // 调用切换后Fargment的onResume()
		}
		currentPageIndex = i;

		if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
			onExtraPageChangeListener.onExtraPageSelected(i);
		}

	}

	@Override
	public void onPageScrollStateChanged(int i) {
		if (null != onExtraPageChangeListener) { // 如果设置了额外功能接口
			onExtraPageChangeListener.onExtraPageScrollStateChanged(i);
		}
	}

	/**
	 * page切换额外功能接口
	 */
	public static class OnExtraPageChangeListener {
		public void onExtraPageScrolled(int i, float v, int i2) {
		}

		public void onExtraPageSelected(int i) {
		}

		public void onExtraPageScrollStateChanged(int i) {
		}
	}

}
