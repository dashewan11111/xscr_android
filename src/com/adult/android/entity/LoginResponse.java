package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

public class LoginResponse extends StatusInfo {
	private LoginData data;

	public LoginResponse() {
		super();
	}

	public LoginData getData() {
		return data;
	}

	public void setData(LoginData data) {
		this.data = data;
	}
}
