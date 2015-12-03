package com.adult.android.model;

import android.util.Log;

import com.adult.android.entity.CategoryCountryResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

//获取全球信息
public class CategoryCountryModel {
	public void getCategoryCountry(final CategoryCountryListener listener) {
		InputBean inputBean = new InputBean();
		// 初始化请求参数
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams.getCountry);
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);

		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean,
				CategoryCountryResponse.class,
				new HttpResponseListener<CategoryCountryResponse>() {
					@Override
					public void onStart() {
						Log.i("ws", "CategoryCountryModel.onStart");
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.getCountryFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.getCountryFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException();
						re.setResultMsg("请求错误，请重试");
						listener.getCountryFail(re);
					}

					@Override
					public void onFinish() {

					}

					@Override
					public void onSuccess(CategoryCountryResponse t) {
						// TODO Auto-generated method stub
						listener.getCategoryCountrySuccess(t);

					}
				});

	}

	public static interface CategoryCountryListener {
		void getCategoryCountrySuccess(CategoryCountryResponse t);

		void getCountryFail(ResponseException e);

	}
}
