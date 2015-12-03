package com.adult.android.entity;

import java.io.Serializable;

public class EPayResult implements Serializable {

	private String msg;

	private String msgCode;

	private String payStatus;

	private String localFlowNo;

	private String payAmount;

	private String payModel;

	private String orderOrPayId;

	private String action;

	private EpayRequestBody requestBody;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getLocalFlowNo() {
		return localFlowNo;
	}

	public void setLocalFlowNo(String localFlowNo) {
		this.localFlowNo = localFlowNo;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayModel() {
		return payModel;
	}

	public void setPayModel(String payModel) {
		this.payModel = payModel;
	}

	public String getOrderOrPayId() {
		return orderOrPayId;
	}

	public void setOrderOrPayId(String orderOrPayId) {
		this.orderOrPayId = orderOrPayId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public EpayRequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(EpayRequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
