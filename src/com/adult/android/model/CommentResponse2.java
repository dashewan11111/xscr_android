package com.adult.android.model;

import java.util.List;

import com.adult.android.entity.BaseEntity;
import com.adult.android.entity.Comment2;

public class CommentResponse2 extends BaseEntity {

	private List<Comment2> commentList;

	public List<Comment2> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment2> commentList) {
		this.commentList = commentList;
	}
}
