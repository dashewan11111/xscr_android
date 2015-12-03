package com.adult.android.entity;

import java.util.List;

public class ProductDetailsDto extends Product2 {

	private int buyerCount;// 购买者数量

	private int commentCount; // 评论数量

	private float commentPoint;// 综合评分

	private List<ServiceDto> serviceList;// 服务列表

	private List<Comment2> commentList;// 评论列表

	private List<Promotion> promotionList;// 优惠策略

	private String detailUrl;// 图文详情

	public int getBuyerCount() {
		return buyerCount;
	}

	public void setBuyerCount(int buyerCount) {
		this.buyerCount = buyerCount;
	}

	public List<ServiceDto> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceDto> serviceList) {
		this.serviceList = serviceList;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public float getCommentPoint() {
		return commentPoint;
	}

	public void setCommentPoint(float commentPoint) {
		this.commentPoint = commentPoint;
	}

	public List<Comment2> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment2> commentList) {
		this.commentList = commentList;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
}
