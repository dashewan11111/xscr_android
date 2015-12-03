package com.adult.android.entity;

/**
 * 评价内容对象
 * @author 龚旬
 *
 */
public class Comment extends BaseEntity {
	
	private int level;
	private String userName;
	private String creatTime;
	private String value;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
} 
