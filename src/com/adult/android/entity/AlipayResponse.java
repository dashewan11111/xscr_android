package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class AlipayResponse extends StatusInfo {

	private AlipayResult data = null;

	public AlipayResult getData() {
		return data;
	}

	public void setData(AlipayResult data) {
		this.data = data;
	}

}
