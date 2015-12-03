package com.adult.android.entity;

public class BrandInfo extends BaseEntity {
	private String id;
	private String name;
	private String sortvals;
	private String count;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public BrandInfo() {
		super();
		// TODO Auto-generated constructor stub
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
