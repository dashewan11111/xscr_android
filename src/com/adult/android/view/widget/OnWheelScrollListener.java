package com.adult.android.view.widget;

/**
 * @ClassName: OnWheelScrollListener
 * @Description: wheel scroll listener interface
 * @author 潘学坤
 * @date 2015年3月9日 上午10:24:18
 * 
 */
public interface OnWheelScrollListener {
	/**
	 * Callback method to be invoked when scrolling started.
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed.
	 */
	void onScrollingStarted(WheelView wheel);

	/**
	 * Callback method to be invoked when scrolling ended.
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed.
	 */
	void onScrollingFinished(WheelView wheel);
}
