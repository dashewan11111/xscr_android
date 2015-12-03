package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

/**
 * 
 * @author GongXun  
 *
 * @2015年3月26日
 *
 * @descripte
 *
 *     用户的模块的基本信息封装
 */
public class UserInfoResponse extends StatusInfo{
	private UserInfo data;

	public UserInfoResponse() {
		super();
	}
	public UserInfo getData() {
		return data;
	}
	public void setData(UserInfo data) {
		this.data = data;
	}

}
