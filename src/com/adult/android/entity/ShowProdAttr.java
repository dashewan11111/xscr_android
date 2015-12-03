package com.adult.android.entity;

import java.util.List;

public class ShowProdAttr extends BaseEntity {
	 private String productIdV;
	 private String attrIdV;
	 private String attrNameEn;
	 private String attrNameCn;
	 private List<ProdAttrVal> ProdAttrVals;
	public ShowProdAttr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProductIdV() {
		return productIdV;
	}
	public void setProductId(String productIdV) {
		this.productIdV = productIdV;
	}
	public String getAttrId() {
		return attrIdV;
	}
	public void setAttrIdV(String attrIdV) {
		this.attrIdV = attrIdV;
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
