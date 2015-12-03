package com.adult.android.entity;

public class EpayRequestBody {

	private String merchantId;

	private String orderSEQ;

	private String orderReqTranSEQ;

	private String orderReqTime;

	private String orderAmt;

	private String tranCode;

	private String placeAndOrderType;

	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOrderSEQ() {
		return orderSEQ;
	}
	public void setOrderSEQ(String orderSEQ) {
		this.orderSEQ = orderSEQ;
	}
	public String getOrderReqTranSEQ() {
		return orderReqTranSEQ;
	}
	public void setOrderReqTranSEQ(String orderReqTranSEQ) {
		this.orderReqTranSEQ = orderReqTranSEQ;
	}
	public String getOrderReqTime() {
		return orderReqTime;
	}
	public void setOrderReqTime(String orderReqTime) {
		this.orderReqTime = orderReqTime;
	}
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public String getPlaceAndOrderType() {
		return placeAndOrderType;
	}
	public void setPlaceAndOrderType(String placeAndOrderType) {
		this.placeAndOrderType = placeAndOrderType;
	}

}
