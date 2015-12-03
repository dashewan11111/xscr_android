package com.adult.android.view.product;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by RoyJing on 15/6/29.
 */
public class ProductDetailsSecondView extends LinearLayout {
	private List<OnCanPullDownListener> listeners = new ArrayList<>();

	public ProductDetailsSecondView(Context context) {
		this(context, null);
	}

	public ProductDetailsSecondView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void addOnPullDownListener(OnCanPullDownListener l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	public boolean canPullDown() {
		for (OnCanPullDownListener listener : listeners) {
			if (listener.isForeground() && listener.allowPullDown()) {
				return true;
			}
		}
		return false;
	}

	public static interface OnCanPullDownListener {
		boolean allowPullDown();

		boolean isForeground();
	}
}
