package com.adult.android.entity;

public class CategorySort extends BaseEntity {
	private String cateDispId;
	private String dispName;
	private String dispNameCn;
	private String parCateDispId;
	private String catePubId;
	private String picUrl;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CategorySort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getCateDispId() {
		return cateDispId;
	}

	public void setCateDispId(String cateDispId) {
		this.cateDispId = cateDispId;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getDispNameCn() {
		return dispNameCn;
	}

	public void setDispNameCn(String dispNameCn) {
		this.dispNameCn = dispNameCn;
	}

	public String getParCateDispId() {
		return parCateDispId;
	}

	public void setParCateDispId(String parCateDispId) {
		this.parCateDispId = parCateDispId;
	}

	public String getCatePubId() {
		return catePubId;
	}

	public void setCatePubId(String catePubId) {
		this.catePubId = catePubId;
	}

}
