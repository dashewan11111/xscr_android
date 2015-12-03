package com.adult.android.entity;

import java.util.ArrayList;

import com.adult.android.model.internet.bean.StatusInfo;

public class SearchHotResponse extends StatusInfo {
	private ArrayList<SearchHot> data;

	public ArrayList<SearchHot> getData() {
		return data;
	}

	public void setData(ArrayList<SearchHot> data) {
		this.data = data;
	}
}
