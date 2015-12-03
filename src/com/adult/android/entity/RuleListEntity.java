package com.adult.android.entity;

/**
 * Created by RoyJing on 15/8/17.
 */
public class RuleListEntity extends BaseEntity {
    private long filteredRuleId;
    private int promotionType;
    private String ruleName;
    private int dispAttrVal;
    private int ruleTerm;
    private long endDate;
    public RuleListEntity() {
        super();
    }

    public long getFilteredRuleId() {
        return filteredRuleId;
    }

    public void setFilteredRuleId(long filteredRuleId) {
        this.filteredRuleId = filteredRuleId;
    }


    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(int promotionType) {
        this.promotionType = promotionType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getDispAttrVal() {
        return dispAttrVal;
    }

    public void setDispAttrVal(int dispAttrVal) {
        this.dispAttrVal = dispAttrVal;
    }

    public int getRuleTerm() {
        return ruleTerm;
    }

    public void setRuleTerm(int ruleTerm) {
        this.ruleTerm = ruleTerm;
    }
}
