package com.adult.android.entity;

import java.util.List;

public class ScreenResult extends BaseEntity {
	private int hits;
	private int numFetch;
	private List<CategoryInfo> items;
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getNumFetch() {
		return numFetch;
	}
	public void setNumFetch(int numFetch) {
		this.numFetch = numFetch;
	}
	public List<CategoryInfo> getItems() {
		return items;
	}
	public void setItems(List<CategoryInfo> items) {
		this.items = items;
	}

}
