package com.adult.android.entity;


/**
 * 个人中心订单数量
 */
public class OrderCountInfo extends BaseEntity{

    private String waitPayQty;
    private String waitReceiptQty;
    private String waitEvaluateQty;
    private String canRefundQty;

    public String getWaitReceiptQty() {
        return waitReceiptQty;
    }

    public void setWaitReceiptQty(String waitReceiptQty) {
        this.waitReceiptQty = waitReceiptQty;
    }

    public String getWaitPayQty() {
        return waitPayQty;
    }

    public void setWaitPayQty(String waitPayQty) {
        this.waitPayQty = waitPayQty;
    }

    public String getWaitEvaluateQty() {
        return waitEvaluateQty;
    }

    public void setWaitEvaluateQty(String waitEvaluateQty) {
        this.waitEvaluateQty = waitEvaluateQty;
    }

    public String getCanRefundQty() {
        return canRefundQty;
    }

    public void setCanRefundQty(String canRefundQty) {
        this.canRefundQty = canRefundQty;
    }
}
