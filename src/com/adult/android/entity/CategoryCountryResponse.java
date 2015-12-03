package com.adult.android.entity;

import java.util.ArrayList;

import com.adult.android.model.internet.bean.StatusInfo;

public class CategoryCountryResponse extends StatusInfo {
	private ArrayList<CountryInfo> data;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CategoryCountryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CountryInfo> getData() {
		return data;
	}

	public void setData(ArrayList<CountryInfo> data) {
		this.data = data;
	}
}
