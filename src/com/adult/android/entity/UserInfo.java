package com.adult.android.entity;

/**
 * 
 * @author GongXun
 * 
 * @2015年3月24日
 * 
 * @descripte
 * 
 *            当前登录用户的信息封装 
 */
public class UserInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String nickName;
	private String mobile;
	private String email;
	private int status;
	private int eduLevelId;
	private int professionId;
	private int monIncomeId;
	private int gradeId;
	private int gender;
	private boolean isBirthdayPass;
	private int provinceId;
	private int cityId;
	private int areaId;
	private long createTime;
	private long lastLoginTime;
	private boolean maritalStatus;
	private long birthday;
	private String realName;
	private String idCard;
	
	
	
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
	private String score;
	private String upgradescore;
	private boolean realNameValidate;
	public boolean getRealNameValidate() {
		return realNameValidate;
	}
	public void setRealNameValidate(boolean realNameValidate) {
		this.realNameValidate = realNameValidate;
	}
	private boolean changed;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getEduLevelId() {
		return eduLevelId;
	}
	public void setEduLevelId(int eduLevelId) {
		this.eduLevelId = eduLevelId;
	}
	public int getProfessionId() {
		return professionId;
	}
	public void setProfessionId(int professionId) {
		this.professionId = professionId;
	}
	public int getMonIncomeId() {
		return monIncomeId;
	}
	public void setMonIncomeId(int monIncomeId) {
		this.monIncomeId = monIncomeId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public boolean getIsBirthdayPass() {
		return isBirthdayPass;
	}
	public void setIsBirthdayPass(boolean isBirthdayPass) {
		this.isBirthdayPass = isBirthdayPass;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public boolean getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUpgradescore() {
		return upgradescore;
	}
	public void setUpgradescore(String upgradescore) {
		this.upgradescore = upgradescore;
	}
	public boolean getChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	
}
