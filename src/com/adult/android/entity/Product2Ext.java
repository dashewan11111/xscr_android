package com.adult.android.entity;

public class Product2Ext extends Product2 {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7400510574446723598L;

	private Sku2 sku;

	private boolean isChecked = true;// 是否被选中

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Sku2 getSku() {
		return sku;
	}

	public void setSku(Sku2 sku) {
		this.sku = sku;
	}

}
