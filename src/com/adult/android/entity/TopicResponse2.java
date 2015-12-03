package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

public class TopicResponse2 extends StatusInfo {

	private List<TopicDTO> topicList = null;

	public List<TopicDTO> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<TopicDTO> topicList) {
		this.topicList = topicList;
	}

}
