package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

/**
 * User: liyuj<liyuj@cigmall.cn>
 * Date: 2015-08-06
 * Time: 15:41
 * FIXME
 */
public class WxpayResponse extends StatusInfo {

    private WxpayData data;

    public WxpayData getData() {
        return data;
    }

    public void setData(WxpayData data) {
        this.data = data;
    }
}
