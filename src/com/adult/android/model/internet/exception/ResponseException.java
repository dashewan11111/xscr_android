package com.adult.android.model.internet.exception;

public class ResponseException extends Exception {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1797689500576246895L;
	/**
	 * 请求返回code
	 */
	private String resultCode;
	/**
	 * 请求返回的详细信息
	 */
	private String resultMsg;

	public ResponseException() {
	}

	public ResponseException(Throwable throwable) {
		super(throwable);
	}

	public ResponseException(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}
