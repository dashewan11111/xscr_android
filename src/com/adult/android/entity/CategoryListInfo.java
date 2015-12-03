package com.adult.android.entity;

import java.util.List;

public class CategoryListInfo extends BaseEntity {
	private int hits;
	private int numFetch;
	private List<CategoryInfo> items;
	private List<BrandInfo> listBrandValue;
	private List<PriceInfo> listPriceValue;
	private List<CyidInfo> listCyidValue;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CategoryListInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public List<BrandInfo> getListBrandValue() {
		return listBrandValue;
	}

	public void setListBrandValue(List<BrandInfo> listBrandValue) {
		this.listBrandValue = listBrandValue;
	}

	public List<PriceInfo> getListPriceValue() {
		return listPriceValue;
	}

	public void setListPriceValue(List<PriceInfo> listPriceValue) {
		this.listPriceValue = listPriceValue;
	}

	public List<CyidInfo> getListCyidValue() {
		return listCyidValue;
	}

	public void setListCyidValue(List<CyidInfo> listCyidValue) {
		this.listCyidValue = listCyidValue;
	}

}
