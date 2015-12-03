package com.adult.android.entity;

import java.util.ArrayList;

import com.adult.android.model.internet.bean.StatusInfo;

public class CategorySortResponse extends StatusInfo {
	private ArrayList<CategorySort> data;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CategorySortResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CategorySort> getData() {
		return data;
	}

	public void setData(ArrayList<CategorySort> data) {
		this.data = data;
	}
}
