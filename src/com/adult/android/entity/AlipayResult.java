package com.adult.android.entity;

import java.io.Serializable;

/**
 * 支付宝WEB端支付结果
 * 
 * @author Administrator
 * 
 */
public class AlipayResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4576281996067421594L;

	private String msg;

	private String payStatus;

	private String localFlowNo;

	private String payAmount;

	private MobilePayRequest requestBody;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public MobilePayRequest getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(MobilePayRequest requestBody) {
		this.requestBody = requestBody;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
