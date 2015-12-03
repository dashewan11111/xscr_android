package com.adult.android.entity;

import java.util.List;

/**
 * Created by huangchao on 8/21/2015.
 *
 * 各种活动详情
 */
public class FullOrBuyGiftsInfo extends BaseEntity{

    private String promotionName;

    private String endTime;
    /**活动类型**/
    private String promotionType;
    /**赠品**/
    private List<GiftsInfo> gifts;

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public List<GiftsInfo> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftsInfo> gifts) {
        this.gifts = gifts;
    }

    @Override
    public String toString() {
        return "FullOrBuyGiftsInfo{" +
                "promotionName='" + promotionName + '\'' +
                ", endTime='" + endTime + '\'' +
                ", promotionType='" + promotionType + '\'' +
                ", gifts=" + gifts +
                '}';
    }
}
