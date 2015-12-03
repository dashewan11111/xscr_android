package com.adult.android.entity;

/**
 * Created by huangchao on 8/21/2015.
 *
 * 赠品信息
 */
public class GiftsInfo extends BaseEntity{
    /**赠品id**/
    private String productId;
    /**赠品skuId**/
    private String skuId;

    private String productName;
    /**赠品规格名称**/
    private String skuName;
    /**赠品数量**/
    private String qty;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "GiftsInfo{" +
                "productId='" + productId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", productName='" + productName + '\'' +
                ", skuName='" + skuName + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
