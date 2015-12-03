package com.adult.android.entity;

import java.util.ArrayList;

import com.adult.android.model.internet.bean.StatusInfo;

public class SuggestionResponse extends StatusInfo {
	private ArrayList<SuggestionInfo> data;

	public ArrayList<SuggestionInfo> getData() {
		return data;
	}

	public void setData(ArrayList<SuggestionInfo> data) {
		this.data = data;
	}
}
