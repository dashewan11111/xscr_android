package com.adult.android.entity;

import java.math.BigDecimal;

/**
 * Created by RoyJing on 15/8/21.
 */
public class SkuGiftDto extends BaseEntity {
    //商品id
    private Long pId;
    //商品skuid
    private Long skuId;
    //赠品商品名称
    private String pName;
    //赠品sku名称
    private String skuName;
    //赠品商品图片地址
    private String imgURL;
    //赠品数量
    private Integer qty;
    //赠品商品价格 默认为0
    private BigDecimal price;
    //赠品品牌id
    private String brandId;
    //赠品发布类目id
    private String categoryId;
    public Long getpId() {
        return pId;
    }
    public void setpId(Long pId) {
        this.pId = pId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public String getImgURL() {
        return imgURL;
    }
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getBrandId() {
        return brandId;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
