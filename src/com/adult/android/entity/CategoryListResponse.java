package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class CategoryListResponse extends StatusInfo {
	private CategoryListInfo data;

	public CategoryListResponse() {
		super();
	}

	public CategoryListInfo getData() {
		return data;
	}

	public void setData(CategoryListInfo data) {
		this.data = data;
	}

	
}
