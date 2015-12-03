package com.adult.android.model;

import android.util.Log;

import com.adult.android.entity.CategorySortResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

/**
 * 品类 返回数据
 * http://api.ccigmall.com/router?v=1.0&method=category.disp.get&appKey=100001
 * */
public class CategorySortModel {
	public void getAllSort(final CategorySortListener listener) {
		// TODO Auto-generated method stub
		InputBean inputBean = new InputBean();
		// 初始化请求参数
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams.categorySort);
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);

		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean,
				CategorySortResponse.class,
				new HttpResponseListener<CategorySortResponse>() {
					@Override
					public void onStart() {
						Log.i("ws", "CategorySortModel.onStart");
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.getSortFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.getSortFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException();
						re.setResultMsg("请求错误，请重试");
						listener.getSortFail(re);
					}

					@Override
					public void onFinish() {

					}

					@Override
					public void onSuccess(CategorySortResponse t) {
						// TODO Auto-generated method stub
						listener.getCategorySortSuccess(t);
					}
				});
	}

	public static interface CategorySortListener {
		void getCategorySortSuccess(CategorySortResponse t);

		void getSortFail(ResponseException e);

	}

}
