package com.adult.android.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomePageDto extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4274692922741320603L;

	/**
	 * 内容信息总数
	 */
	private long totalAmount = 0;

	/**
	 * 内容信息每页的条数
	 */
	private int pageSize = 10;

	/**
	 * 轮播图或者内容信息
	 * 
	 * 轮播图 key=carousel value=List<CustomerContentDto> 内容列表 key=contentList
	 * value=List<CustomerContentDto>
	 */
	Map<String, List<HomeFinalItem>> indexContent = new LinkedHashMap<String, List<HomeFinalItem>>();

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, List<HomeFinalItem>> getIndexContent() {
		return indexContent;
	}

	public void setIndexContent(
			Map<String, List<HomeFinalItem>> indexContent) {
		this.indexContent = indexContent;
	}

}
