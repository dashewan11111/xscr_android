package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class CategoryResponse extends StatusInfo {

	private List<CategoryFrist> data = null;

	public CategoryResponse() {
		super();
	}

	public List<CategoryFrist> getData() {
		return data;
	}

	public void setData(List<CategoryFrist> data) {
		this.data = data;
	}
}
