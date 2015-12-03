package com.adult.android.entity;

public class ProductRule extends BaseEntity {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -153023547597139918L;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleAlias() {
		return ruleAlias;
	}

	public void setRuleAlias(String ruleAlias) {
		this.ruleAlias = ruleAlias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(String dateEffective) {
		this.dateEffective = dateEffective;
	}

	public String getDateExpires() {
		return dateExpires;
	}

	public void setDateExpires(String dateExpires) {
		this.dateExpires = dateExpires;
	}

	public String getRulePlatform() {
		return rulePlatform;
	}

	public void setRulePlatform(String rulePlatform) {
		this.rulePlatform = rulePlatform;
	}

	public String getRuleScope() {
		return ruleScope;
	}

	public void setRuleScope(String ruleScope) {
		this.ruleScope = ruleScope;
	}

	public String getIsMayfans() {
		return isMayfans;
	}

	public void setIsMayfans(String isMayfans) {
		this.isMayfans = isMayfans;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getLimtedQuantity() {
		return limtedQuantity;
	}

	public void setLimtedQuantity(String limtedQuantity) {
		this.limtedQuantity = limtedQuantity;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}

	private String ruleId;
	private String ruleName;
	private String ruleAlias;
	private String type;
	private String dateEffective;
	private String dateExpires;
	private String rulePlatform;
	private String ruleScope;
	private String isMayfans;
	private String slogan;
	private String limtedQuantity;
	private String createTime;
	private String processState;
	private String ruleContent;

}
