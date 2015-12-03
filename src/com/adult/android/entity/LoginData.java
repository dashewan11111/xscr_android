package com.adult.android.entity;

public class LoginData extends BaseEntity {
	private String sessionId;

	public LoginData() {
		super();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
