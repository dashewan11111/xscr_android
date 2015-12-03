package com.adult.android.model;

import com.adult.android.entity.VersionInfo;
import com.adult.android.entity.VersionInfoResponse;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

public class CartProductModel {
	public void checkUpgrade(final OnCheckVersionListener listener) {
		InputBean inputBean = new InputBean();
		// inputBean.putQueryParam("", "");// 初始化请求参数
		InternetClient.get("", inputBean, VersionInfoResponse.class,
				new HttpResponseListener<VersionInfoResponse>() {
					@Override
					public void onStart() {

					}

					@Override
					public void onSuccess(VersionInfoResponse t) {
						/** 经过对版本信息的处理，得出相应的结果，callback给调用者。 */
						listener.onNotNeedUpdateVersion();
						// listener.onNeedUpdateVerion(t.getData());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onCheckVersionFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onCheckVersionFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						listener.onCheckVersionFail(new ResponseException(
								throwable));
					}

					@Override
					public void onFinish() {

					}
				});
	}

	public static interface OnCheckVersionListener {
		void onNeedUpdateVerion(final VersionInfo info);

		void onNotNeedUpdateVersion();

		void onCheckVersionFail(final ResponseException e);
	}
}