package com.adult.android.view.widget;

/**
 * @ClassName: OnWheelClickedListener
 * @Description: 点击监听接口
 * @author 潘学坤
 * @date 2015年3月9日 上午10:23:18
 * 
 */
public interface OnWheelClickedListener {
	/**
	 * Callback method to be invoked when current item clicked
	 * 
	 * @param wheel
	 *            the wheel view
	 * @param itemIndex
	 *            the index of clicked item
	 */
	void onItemClicked(WheelView wheel, int itemIndex);
}
