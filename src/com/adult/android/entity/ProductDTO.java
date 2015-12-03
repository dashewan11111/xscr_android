package com.adult.android.entity;

/**
 * User: liyuj<liyuj@cigmall.cn>
 * Date: 2015-08-17
 * Time: 17:06
 * FIXME 
 */
public class ProductDTO extends BaseEntity{

    private String productId;
    private String pageType;
    private String startDate;
    private String endDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
