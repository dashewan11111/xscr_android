package com.adult.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class LLayoutToListView extends LinearLayout {
	
	private BaseAdapter baseAdapter;

	public LLayoutToListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public LLayoutToListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 绑定布局
	 */
	public void bindLinearLayout() {
        this.removeAllViews();
		if (baseAdapter != null) {
			int count = baseAdapter.getCount();
			for (int i = 0; i < count; i++) {
				View v = baseAdapter.getView(i, null, null);
				addView(v, i);
			}
		}
    }
	
	/**
	 * 设置适配器
	 * @param baseAdapter
	 */
	public void setLayoutAdapter(BaseAdapter baseAdapter){
		this.baseAdapter = baseAdapter;
		bindLinearLayout();
	}

	public BaseAdapter getLayoutAdapter() {
		return this.baseAdapter;
	}
}
