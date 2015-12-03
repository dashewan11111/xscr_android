package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class EPayResponse extends StatusInfo {

	private EPayResult data = null;

	public EPayResult getData() {
		return data;
	}

	public void setData(EPayResult data) {
		this.data = data;
	}

}
