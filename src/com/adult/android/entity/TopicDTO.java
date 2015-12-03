package com.adult.android.entity;

import java.util.ArrayList;
import java.util.List;

public class TopicDTO {

	private String topicId;

	private String createrId;

	private String createrName;

	private String createrImgUrl;

	private String careaterLevel;

	private String careaterTime;

	private boolean isRecommend;

	private String title;

	private String content;

	private List<ImageUrl> imgList = new ArrayList<ImageUrl>();

	private String latestReplierName;

	private String latestReplyTime;

	private String visitCount;

	private String replyCount;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ImageUrl> getImgList() {
		return imgList;
	}

	public void setImgList(List<ImageUrl> imgList) {
		this.imgList = imgList;
	}

	public String getLatestReplierName() {
		return latestReplierName;
	}

	public void setLatestReplierName(String latestReplierName) {
		this.latestReplierName = latestReplierName;
	}

	public String getLatestReplyTime() {
		return latestReplyTime;
	}

	public void setLatestReplyTime(String latestReplyTime) {
		this.latestReplyTime = latestReplyTime;
	}

	public String getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	public String getCreaterImgUrl() {
		return createrImgUrl;
	}

	public void setCreaterImgUrl(String createrImgUrl) {
		this.createrImgUrl = createrImgUrl;
	}

	public String getCareaterLevel() {
		return careaterLevel;
	}

	public void setCareaterLevel(String careaterLevel) {
		this.careaterLevel = careaterLevel;
	}

	public String getCareaterTime() {
		return careaterTime;
	}

	public void setCareaterTime(String careaterTime) {
		this.careaterTime = careaterTime;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public class ImageUrl {
		String imgUrl;

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

	}
}
