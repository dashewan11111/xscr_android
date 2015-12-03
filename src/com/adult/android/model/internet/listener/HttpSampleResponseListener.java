package com.adult.android.model.internet.listener;

import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;

/**
 * Created by RoyJing on 15/7/23.
 */
public interface HttpSampleResponseListener<T extends Object> {
    public void onStart();

    public void onSuccess(T t);

    public void onHttpException(HttpResponseException e);

    public void onBusinessException(BusinessException e);

    public void onOtherException(Throwable throwable);

    public void onFinish();
}
