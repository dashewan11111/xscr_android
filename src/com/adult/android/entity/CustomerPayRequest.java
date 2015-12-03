package com.adult.android.entity;

import java.math.BigDecimal;

public class CustomerPayRequest {

	// 订单id
	private Long oid;

	// 支付金额
	private BigDecimal pa;

	// 用户id
	private Long cid;

	// 渠道号 3:android; 5:IOS
	private Short cn;

	// 银行支付类型 6 银联支付 9 支付宝
	private Short bpm;

	private String ip;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CustomerPayRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public BigDecimal getPa() {
		return pa;
	}

	public void setPa(BigDecimal pa) {
		this.pa = pa;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Short getCn() {
		return cn;
	}

	public void setCn(Short cn) {
		this.cn = cn;
	}

	public Short getBpm() {
		return bpm;
	}

	public void setBpm(Short bpm) {
		this.bpm = bpm;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "PayRequest [oid=" + oid + ", pa=" + pa + ", cid=" + cid
				+ ", cn=" + cn + ", bpm=" + bpm + ", ip=" + ip + "]";
	}

}
