package com.adult.android.entity;

public class CyidInfo extends BaseEntity {
	private String id;
	private String name;
	private String sortvals;
	private String count;
	private String imgUrl;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CyidInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortvals() {
		return sortvals;
	}

	public void setSortvals(String sortvals) {
		this.sortvals = sortvals;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
