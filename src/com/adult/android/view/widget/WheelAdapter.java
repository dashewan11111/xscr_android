package com.adult.android.view.widget;

/**
 * @ClassName: WheelAdapter
 * @Description: Wheel adapter interface
 * @author 潘学坤
 * @date 2015年3月9日 上午10:26:46
 * 
 */
public interface WheelAdapter {
	/**
	 * Gets items count
	 * 
	 * @return the count of wheel items
	 */
	public int getItemsCount();

	/**
	 * Gets a wheel item by index.
	 * 
	 * @param index
	 *            the item index
	 * @return the wheel item text or null
	 */
	public String getItem(int index);

	/**
	 * Gets maximum item length. It is used to determine the wheel width. If -1
	 * is returned there will be used the default wheel width.
	 * 
	 * @return the maximum item length or -1
	 */
	public int getMaximumLength();
}
