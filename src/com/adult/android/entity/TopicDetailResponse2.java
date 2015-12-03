package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class TopicDetailResponse2 extends StatusInfo {

	public TopicDetailResponse2() {
		super();
	}

	private TopicDTO topic;

	private List<ReplyDto> replyList;

	private List<Rewarder> rewarderList;

	public TopicDTO getTopic() {
		return topic;
	}

	public void setTopic(TopicDTO topic) {
		this.topic = topic;
	}

	public List<ReplyDto> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ReplyDto> replyList) {
		this.replyList = replyList;
	}

	public List<Rewarder> getRewarderList() {
		return rewarderList;
	}

	public void setRewarderList(List<Rewarder> rewarderList) {
		this.rewarderList = rewarderList;
	}

}
