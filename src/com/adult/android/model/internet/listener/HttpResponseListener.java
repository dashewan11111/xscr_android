package com.adult.android.model.internet.listener;

import com.adult.android.model.internet.bean.StatusInfo;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;

public interface HttpResponseListener<T extends StatusInfo> {
	
	public void onStart();

	public void onSuccess(T t);

	public void onHttpException(HttpResponseException e);

	public void onBusinessException(BusinessException e);

	public void onOtherException(Throwable throwable);

	public void onFinish();
}
