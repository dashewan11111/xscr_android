package com.adult.android.entity;

import java.util.List;

public class ProductListInfo extends BaseEntity {
	private List<ProductInfo> result;
	private int totalCount;
	private int totalPage;

	public ProductListInfo() {
		super();
	}

	public List<ProductInfo> getResult() {
		return result;
	}

	public void setResult(List<ProductInfo> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
