package com.adult.android.view.widget;

/**
 * @ClassName: OnWheelChangedListener
 * @Description:
 * @author 潘学坤
 * @date 2015年3月9日 上午10:18:03
 * 
 */
public interface OnWheelChangedListener {
	/**
	 * Callback method to be invoked when current item changed
	 * 
	 * @param wheel
	 *            the wheel view whose state has changed
	 * @param oldValue
	 *            the old value of current item
	 * @param newValue
	 *            the new value of current item
	 */
	void onChanged(WheelView wheel, int oldValue, int newValue);
}