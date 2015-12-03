package com.adult.android.entity;

import java.io.Serializable;

/**
 * User: liyuj<liyuj@cigmall.cn>
 * Date: 2015-08-06
 * Time: 15:48
 * FIXME
 */
public class WxpayData implements Serializable{

    private String localFlowNo;
    private String payStatus;
    private WxpayResult requestParams;

    public String getLocalFlowNo() {
        return localFlowNo;
    }

    public void setLocalFlowNo(String localFlowNo) {
        this.localFlowNo = localFlowNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public WxpayResult getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(WxpayResult requestParams) {
        this.requestParams = requestParams;
    }
}
