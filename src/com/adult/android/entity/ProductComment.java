package com.adult.android.entity;

/**
 * @ClassName: ProductComment
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月1日 下午12:57:48
 * 
 */
public class ProductComment extends BaseEntity {
	private String id;
	private String context;
	private int level;
	private long date;
	private CommentUserInfo userInfo;
	private CommentOrderInfo orderInfo;

	public ProductComment() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public CommentUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(CommentUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public CommentOrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(CommentOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
}
