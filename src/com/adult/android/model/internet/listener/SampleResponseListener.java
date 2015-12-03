package com.adult.android.model.internet.listener;

import com.adult.android.model.internet.bean.StatusInfo;
import com.adult.android.model.internet.exception.ResponseException;

/**
 * @ClassName: SampleResponseListener
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月8日 上午9:50:34
 * 
 */
public interface SampleResponseListener<T extends StatusInfo> {
	void onRequestStart();

	void onRequestSuccess(T t);

	void onRequestFail(ResponseException e);

	void onRequestFinish();
}
