package com.adult.android.model;

import com.adult.android.entity.CategoryListResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

/**
 * *分类页面-推荐商品列表
 * 
 * @author wangshuang
 */
public class CategoryListModel {
	private static CategoryListModel categoryModel = null;

	public static CategoryListModel getInsance() {
		if (null == categoryModel) {
			categoryModel = new CategoryListModel();
		}
		return categoryModel;
	}

	public void getCategoryResult(String searchType, String argument, int page,
			final CategoryListListener listener) {
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		if (searchType != null) {
			inputBean.putQueryParam(searchType, argument);
		}
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams.searchlist);
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam("b2C", "true");
		if (page > 1) {
			inputBean.putQueryParam("pageno", page);
		}
		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean,
				CategoryListResponse.class,
				new HttpResponseListener<CategoryListResponse>() {
					@Override
					public void onStart() {

					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.getCategoryListFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.getCategoryListFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException();
						re.setResultMsg("请求错误，请重试");
						listener.getCategoryListFail(re);
					}

					@Override
					public void onFinish() {

					}

					@Override
					public void onSuccess(CategoryListResponse t) {
						listener.getCategoryResultSuccess(t);
					}
				});

	}

	public static interface CategoryListListener {
		void getCategoryResultSuccess(CategoryListResponse t);

		void getCategoryListFail(ResponseException e);
	}
}
