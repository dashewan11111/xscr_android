package com.adult.android.entity;

/**
 * 支付宝WEB端支付结果
 * 
 * @author Administrator
 * 
 */
public class MobilePayRequest {

	private String notifyUrlMobile;

	private String partner;

	private String private_key;

	private String ali_public_key;

	private String log_path;

	private String input_charset;

	private String sign_type;

	public String getNotifyUrlMobile() {
		return notifyUrlMobile;
	}

	public void setNotifyUrlMobile(String notifyUrlMobile) {
		this.notifyUrlMobile = notifyUrlMobile;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public String getAli_public_key() {
		return ali_public_key;
	}

	public void setAli_public_key(String ali_public_key) {
		this.ali_public_key = ali_public_key;
	}

	public String getLog_path() {
		return log_path;
	}

	public void setLog_path(String log_path) {
		this.log_path = log_path;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
}
