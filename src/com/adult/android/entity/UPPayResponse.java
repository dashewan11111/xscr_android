package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class UPPayResponse extends StatusInfo {

	private UPPayResult data = null;

	public UPPayResult getData() {
		return data;
	}

	public void setData(UPPayResult data) {
		this.data = data;
	}

}
