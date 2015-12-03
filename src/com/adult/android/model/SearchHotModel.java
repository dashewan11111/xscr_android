package com.adult.android.model;

import android.util.Log;

import com.adult.android.entity.SearchHotResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

/**
 * 搜索页 热词 
 * http://api.ccigmall.com/router?v=1.0&method=product.hot.search&appKey=100001
 * */
public class SearchHotModel {
	public void getSearchHot(final SearchHotListener listener) {
		// TODO Auto-generated method stub
		InputBean inputBean = new InputBean();
		// 初始化请求参数
		inputBean.putQueryParam(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,UserParams.hotSearch);
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,ServiceUrlConstants.APP_KEY_VALUE);
		
		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean, SearchHotResponse.class,
				new HttpResponseListener<SearchHotResponse>() {
					@Override
					public void onStart(){
						Log.i("ws","SearchHotModel.onStart");
					}
				
					@Override
					public void onHttpException(HttpResponseException e) {
						listener.getHotFail(e);
					}
					@Override
					public void onBusinessException(BusinessException e) {
				

					}
					@Override
					public void onOtherException(Throwable throwable) {
				

					}
					@Override
					public void onFinish() {

					}
					@Override
					public void onSuccess(SearchHotResponse t) {
						// TODO Auto-generated method stub
						listener.getSearchHotSuccess(t);
						
					}
				});
	}
	public static interface SearchHotListener {
		void getSearchHotSuccess(SearchHotResponse t);
		void getHotFail(HttpResponseException e);

	}
	
}
