package com.adult.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 解决ScrollView嵌套ViewPager出现的滑动冲突问题
 */
public class MyScrollView extends ScrollView {

	private OnScrollViewChangeListner onScrollViewChangeListner;

	public MyScrollView(Context context) {
		super(context);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (null != getOnScrollViewChangeListner()) {
			onScrollViewChangeListner.onScrollChanged(l, t, oldl, oldt);
		}
	}

	public OnScrollViewChangeListner getOnScrollViewChangeListner() {
		return onScrollViewChangeListner;
	}

	public void setOnScrollViewChangeListner(
			OnScrollViewChangeListner onScrollViewChangeListner) {
		this.onScrollViewChangeListner = onScrollViewChangeListner;
	}

	public interface OnScrollViewChangeListner {
		void onScrollChanged(int l, int t, int oldl, int oldt);
	}
}
