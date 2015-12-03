package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class ProductSearchList extends StatusInfo {
	/**命中数*/
	private int hit;
	/**取到的翻页数*/
	private int numFetch;
	/**本页包含的结果集*/
	private List<ProductSearchInfo> info;
	
//	推荐商品列表可能不需要筛选项，目前是公用了CategoryListInfo的数据
	private List<BrandInfo> brandList;
	private List<PriceInfo> priceList;
	private List<CyidInfo> cyidList;
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getNumFetch() {
		return numFetch;
	}
	public void setNumFetch(int numFetch) {
		this.numFetch = numFetch;
	}
	public List<ProductSearchInfo> getInfo() {
		return info;
	}
	public void setInfo(List<ProductSearchInfo> info) {
		this.info = info;
	}
	
	public List<BrandInfo> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<BrandInfo> brandList) {
		this.brandList = brandList;
	}
	public List<PriceInfo> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<PriceInfo> priceList) {
		this.priceList = priceList;
	}
	public List<CyidInfo> getCyidList() {
		return cyidList;
	}
	public void setCyidList(List<CyidInfo> cyidList) {
		this.cyidList = cyidList;
	}
	
	

}
