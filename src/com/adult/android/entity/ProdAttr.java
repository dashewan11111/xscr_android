package com.adult.android.entity;

import java.util.List;

public class ProdAttr extends BaseEntity {
	 private String productId;
     private int attrId;
     private String attrNameEn;
     private String attrNameCn;
     private List<ProdAttrVal> ProdAttrVals;
	public ProdAttr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getAttrId() {
		return attrId;
	}
	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}
	public String getAttrNameEn() {
		return attrNameEn;
	}
	public void setAttrNameEn(String attrNameEn) {
		this.attrNameEn = attrNameEn;
	}
	public String getAttrNameCn() {
		return attrNameCn;
	}
	public void setAttrNameCn(String attrNameCn) {
		this.attrNameCn = attrNameCn;
	}
	public List<ProdAttrVal> getProdAttrVals() {
		return ProdAttrVals;
	}
	public void setProdAttrVals(List<ProdAttrVal> ProdAttrVals) {
		this.ProdAttrVals = ProdAttrVals;
	} 
     
}
