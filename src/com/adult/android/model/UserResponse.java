package com.adult.android.model;

import com.adult.android.entity.UserDto;
import com.adult.android.model.internet.bean.StatusInfo;

public class UserResponse extends StatusInfo {

	private UserDto data;

	public UserDto getData() {
		return data;
	}

	public void setData(UserDto data) {
		this.data = data;
	}
}
