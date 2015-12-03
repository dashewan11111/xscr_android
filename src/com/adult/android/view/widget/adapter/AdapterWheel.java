package com.adult.android.view.widget.adapter;

import android.content.Context;

import com.adult.android.view.widget.WheelAdapter;

/**
 * @ClassName: AdapterWheel
 * @Description: Adapter class for old wheel adapter (deprecated WheelAdapter
 *               class).
 * @author 潘学坤
 * @date 2015年3月9日 上午10:35:39
 * 
 */
public class AdapterWheel extends AbstractWheelTextAdapter {

	// Source adapter
	private WheelAdapter adapter;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the current context
	 * @param adapter
	 *            the source adapter
	 */
	public AdapterWheel(Context context, WheelAdapter adapter) {
		super(context);

		this.adapter = adapter;
	}

	/**
	 * Gets original adapter
	 * 
	 * @return the original adapter
	 */
	public WheelAdapter getAdapter() {
		return adapter;
	}

	@Override
	public int getItemsCount() {
		return adapter.getItemsCount();
	}

	@Override
	protected CharSequence getItemText(int index) {
		return adapter.getItem(index);
	}

}
