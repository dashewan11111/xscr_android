package com.adult.android.entity;

import java.util.List;

public class HomeGroup extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3280928662079523524L;

	private String title;// 分组标题

	private List<HomeFinalItem> dataList;// 分组数据

	private String more;// "更多"内容id(communityId,categoryId)

	private String moreType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<HomeFinalItem> getDataList() {
		return dataList;
	}

	public void setDataList(List<HomeFinalItem> dataList) {
		this.dataList = dataList;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getMoreType() {
		return moreType;
	}

	public void setMoreType(String moreType) {
		this.moreType = moreType;
	}
}
