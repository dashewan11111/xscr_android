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
public class UserBaseInfo extends StatusInfo{
	private Result result;
	private String data;
	public UserBaseInfo(){}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "UserBaseInfo [result=" + result.getMessage()+"_"+result.getResult() + ", data=" + data + "]";
	}
}
