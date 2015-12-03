package com.adult.android.entity;

/**
 * @ClassName: CommentUserInfo
 * @Description:
 * @author
 * @date 2015年4月1日 下午1:06:29
 * 
 */
public class CommentUserInfo extends BaseEntity {
	private String userId;
	private String userPic;
	private String userLevel;
	private int isAnonymity;
	private String userName;
	private int isRetailerAnonymity;
	private int isComment;

	public CommentUserInfo() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIsAnonymity() {
		return isAnonymity;
	}

	public void setIsAnonymity(int isAnonymity) {
		this.isAnonymity = isAnonymity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIsRetailerAnonymity() {
		return isRetailerAnonymity;
	}

	public void setIsRetailerAnonymity(int isRetailerAnonymity) {
		this.isRetailerAnonymity = isRetailerAnonymity;
	}

	public int getIsComment() {
		return isComment;
	}

	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
}
