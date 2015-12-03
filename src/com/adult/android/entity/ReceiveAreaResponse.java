package com.adult.android.entity;

import com.adult.android.model.internet.bean.StatusInfo;

/**
 * Created by Administrator on 2015/6/17.
 */
public class ReceiveAreaResponse extends StatusInfo {
    public ReceiveArea getData() {
        return data;
    }

    public void setData(ReceiveArea data) {
        this.data = data;
    }

    private ReceiveArea data;
}
