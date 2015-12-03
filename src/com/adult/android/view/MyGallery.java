/*
 * Have a nice day.
 * @author YangSong
 * @mail song.yang@kuwo.cn
 */
package com.adult.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class MyGallery extends Gallery {

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyGallery(Context context) {
		super(context);
	}

	private void init() {
		setSpacing(-1);
		setUnselectedAlpha(1f);
		setSoundEffectsEnabled(false);
		setFadingEdgeLength(0);
		setAnimationDuration(1000);
		setFocusableInTouchMode(true);
	}

	public void scrollLeft() {
		onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
	}

	public void scrollRight() {
		onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
	}

	public void scrollLeft(int during) {
		setAnimationDuration(during);
		onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
		setAnimationDuration(1000);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mAttachedChanged != null)
			mAttachedChanged.onTouchEvent(event);
		try {
			return super.onTouchEvent(event);
		} catch (Throwable e) {
			e.printStackTrace();
			return true;
		}
	}
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > (e1.getX() + 50);
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (isScrollingLeft(e1, e2)) {
			scrollLeft();
		} else {
			scrollRight();
		}
		return true;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mAttachedChanged != null) {
			mAttachedChanged.onAttachedToWindow(this);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (mAttachedChanged != null) {
			mAttachedChanged.onDetachedFromWindow(this);
		}
	}

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
	}

	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		return false;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!changed && !isLayout) {
			return;
		}
		super.onLayout(changed, l, t, r, b);
		isLayout = false;
	}

	private boolean isLayout = false;

	public void doRequestLayout() {
		isLayout = true;
		requestLayout();
	}

	private OnWindowAttachedChanged mAttachedChanged;

	public void setOnWindowAttachedChanged(
			OnWindowAttachedChanged onWindowAttachedChanged) {
		this.mAttachedChanged = onWindowAttachedChanged;
	}

	public static interface OnWindowAttachedChanged {
		public void onAttachedToWindow(MyGallery view);

		public void onDetachedFromWindow(MyGallery view);

		public void onTouchEvent(MotionEvent event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN :
				this.requestFocus();
				getParent().requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_CANCEL :
			case MotionEvent.ACTION_UP :
				getParent().requestDisallowInterceptTouchEvent(false);
				break;
		}
		super.dispatchTouchEvent(ev);
		return true;
	}

}
