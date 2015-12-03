package com.adult.android.entity;

import java.util.List;

public class HomeGroupDto extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3280928662079523524L;

	private String title;// 分组标题

	private List<HomeItemDto> dataList;// 分组数据

	private String more;// "更多"内容id(communityId,categoryId)

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<HomeItemDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<HomeItemDto> dataList) {
		this.dataList = dataList;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}
}
