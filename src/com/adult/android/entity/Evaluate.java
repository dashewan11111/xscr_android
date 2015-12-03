package com.adult.android.entity;

import java.util.List;

public class Evaluate extends BaseEntity {
	private String totalComments;
	private String praise;
	private List<Comment> comments;
	public Evaluate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTotalComments() {
		return totalComments;
	}
	public void setTotalComments(String totalComments) {
		this.totalComments = totalComments;
	}
	public String getPraise() {
		return praise;
	}
	public void setPraise(String praise) {
		this.praise = praise;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
