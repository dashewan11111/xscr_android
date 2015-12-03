package com.adult.android.entity;

import java.util.List;

public class ProdAttrVal extends BaseEntity {
	private String productIdV;
	private String attrIdV;
	private String attrValIdV;
	private String attrValNameEn;
	private String attrValNameCn;
	private List<String> images;

	public ProdAttrVal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductIdV() {
		return productIdV;
	}

	public void setProductIdV(String productIdV) {
		this.productIdV = productIdV;
	}

	public String getAttrIdV() {
		return attrIdV;
	}

	public void setAttrIdV(String attrIdV) {
		this.attrIdV = attrIdV;
	}

	public String getAttrValIdV() {
		return attrValIdV;
	}

	public void setAttrValIdV(String attrValIdV) {
		this.attrValIdV = attrValIdV;
	}

	public String getAttrValNameEn() {
		return attrValNameEn;
	}

	public void setAttrValNameEn(String attrValNameEn) {
		this.attrValNameEn = attrValNameEn;
	}

	public String getAttrValNameCn() {
		return attrValNameCn;
	}

	public void setAttrValNameCn(String attrValNameCn) {
		this.attrValNameCn = attrValNameCn;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
