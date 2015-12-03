package com.adult.android.entity;

public class UserDto extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8340177731383737949L;

	private String cUserId;
	private String accountName;
	private String loginName;
	private String mobile;
	private String password;
	private String createDate;
	private String sex;
	private String delFlag;
	private String vLevel;

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getvLevel() {
		return vLevel;
	}

	public void setvLevel(String vLevel) {
		this.vLevel = vLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
