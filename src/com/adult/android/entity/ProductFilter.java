package com.adult.android.entity;

import java.util.List;

public class ProductFilter {

	private String filterId;

	private String filterName;

	private List<ProductFilterValue> filterValueList;

	public String getFilterId() {
		return filterId;
	}

	public void setFilterId(String filterId) {
		this.filterId = filterId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<ProductFilterValue> getFilterValueList() {
		return filterValueList;
	}

	public void setFilterValueList(List<ProductFilterValue> filterValueList) {
		this.filterValueList = filterValueList;
	}
}
