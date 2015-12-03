package com.adult.android.entity;

import java.util.List;

public class ProductListDto {

	private List<Product2ForList> productList;

	private List<ProductFilter> filterList;

	public List<Product2ForList> getProductList() {
		return productList;
	}

	public void setProductList(List<Product2ForList> productList) {
		this.productList = productList;
	}

	public List<ProductFilter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<ProductFilter> filterList) {
		this.filterList = filterList;
	}
}
