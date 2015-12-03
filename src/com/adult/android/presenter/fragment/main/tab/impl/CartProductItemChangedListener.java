package com.adult.android.presenter.fragment.main.tab.impl;

/**
 * 
 * @ClassName: CartProductItemChangedListener
 * @Description: 购物车商品状态改变监听器
 * @author 潘学坤
 * @date 2015年2月4日 下午2:18:36
 * 
 */
public interface CartProductItemChangedListener {
	/**
	 * 商品数量改变
	 */
	public abstract void itemNumChanged(int position, int num);

	/**
	 * 商品选中状态
	 */
	public abstract void itemCheckChanged(int position, boolean isCheck);
}
