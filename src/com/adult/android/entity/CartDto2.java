package com.adult.android.entity;

import java.util.List;

public class CartDto2 {

	private String cartCount; // 购物车数量

	private String totalAmount;// 购物车总金额

	private List<Product2Ext> productList;// 商品列表

	public String getCartCount() {
		return cartCount;
	}

	public void setCartCount(String cartCount) {
		this.cartCount = cartCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Product2Ext> getProductList() {
		return productList;
	}

	public void setProductList(List<Product2Ext> productList) {
		this.productList = productList;
	}
}
