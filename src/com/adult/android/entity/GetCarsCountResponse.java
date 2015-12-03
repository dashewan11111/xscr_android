package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class GetCarsCountResponse extends StatusInfo {

	private String data = null;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public GetCarsCountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
