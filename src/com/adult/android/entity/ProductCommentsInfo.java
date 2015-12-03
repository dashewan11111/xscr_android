package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: ProductCommentsInfo
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月1日 下午12:56:06
 * 
 */
public class ProductCommentsInfo extends BaseEntity {
	private int totalCount;
	private int okCount;
	private int goodCount;
	private int badCount;
	private int goodRatio;
	private int okRatio;
	private int badRatio;
	List<ProductComment> comments;

	public ProductCommentsInfo() {
		super();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getOkCount() {
		return okCount;
	}

	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getBadCount() {
		return badCount;
	}

	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}

	public int getGoodRatio() {
		return goodRatio;
	}

	public void setGoodRatio(int goodRatio) {
		this.goodRatio = goodRatio;
	}

	public int getOkRatio() {
		return okRatio;
	}

	public void setOkRatio(int okRatio) {
		this.okRatio = okRatio;
	}

	public int getBadRatio() {
		return badRatio;
	}

	public void setBadRatio(int badRatio) {
		this.badRatio = badRatio;
	}

	public List<ProductComment> getComments() {
		return comments;
	}

	public void setComments(List<ProductComment> comments) {
		this.comments = comments;
	}
}
