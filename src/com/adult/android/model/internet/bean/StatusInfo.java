package com.adult.android.model.internet.bean;

import com.adult.android.entity.Result;

public class StatusInfo {

	private Result result;

	public StatusInfo() {
	}

	public StatusInfo(Result result) {
		super();
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
