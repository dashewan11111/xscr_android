package com.adult.android.entity;

import java.io.Serializable;

import com.adult.android.model.internet.bean.StatusInfo;

public class OrderCommitResponse extends StatusInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7249345485671831672L;

	private SubOrderDTO data = null;

	public SubOrderDTO getData() {
		return data;
	}

	public void setData(SubOrderDTO data) {
		this.data = data;
	}

}
