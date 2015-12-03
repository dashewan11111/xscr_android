package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: Specification
 * @Description: 产品规格
 * @author 潘学坤
 * @date 2015年3月5日 上午11:25:57
 * 
 */
public class Specification {
	private String specificationName;
	private List<String> specificationTypeList;
	private int type; // 0：列表模式，1：按钮模式
	private int productNum;

	public Specification() {

	}

	public Specification(String specificationName,
			List<String> specificationTypeList, int type, int productNum) {
		this.specificationName = specificationName;
		this.specificationTypeList = specificationTypeList;
		this.type = type;
		this.productNum = productNum;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public List<String> getSpecificationTypeList() {
		return specificationTypeList;
	}

	public void setSpecificationTypeList(List<String> specificationTypeList) {
		this.specificationTypeList = specificationTypeList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

}
