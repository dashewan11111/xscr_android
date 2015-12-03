package com.adult.android.entity;

import java.util.List;

public class ShowProdAttrVal extends BaseEntity {
	private int productId;
    private int attrId;
    private int attrValId;
    private String attrValNameEn;
    private String attrValNameCn;
    private List<String> images;
	public ShowProdAttrVal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAttrId() {
		return attrId;
	}
	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}
	public int getAttrValId() {
		return attrValId;
	}
	public void setAttrValId(int attrValId) {
		this.attrValId = attrValId;
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
