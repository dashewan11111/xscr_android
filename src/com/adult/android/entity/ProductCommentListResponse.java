package com.adult.android.entity;

import java.util.List;

import com.adult.android.model.internet.bean.StatusInfo;

/**
 * @ClassName: ProductCommentInfo
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月25日 下午7:15:49
 * 
 */
public class ProductCommentListResponse extends StatusInfo {
	private List<ProductObject> data;

	public ProductCommentListResponse() {
		super();
	}

	public List<ProductObject> getData() {
		return data;
	}

	public void setData(List<ProductObject> data) {
		this.data = data;
	}
}
