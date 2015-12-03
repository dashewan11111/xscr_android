package com.adult.android.model;

import com.adult.android.entity.SuggestionResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

/**
 * 联想词获取
 * http://api.ccigmall.com/router?v=1.0&keyword=f&method=product.suggestion&appKey=100001&b2C="true"
 * */
public class SuggestionModel {
	public void getSuggestionList(String keyword,final SuggestionListener listener) {
		// TODO Auto-generated method stub
		InputBean inputBean = new InputBean();
//		keyword 可变参数
		inputBean.putQueryParam(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam("keyword", keyword);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,"product.suggestion");
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam("b2C", "true");
		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean, SuggestionResponse.class,
				new HttpResponseListener<SuggestionResponse>() {
					@Override
					public void onStart(){
					}
				
					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onFail(e);
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
					public void onSuccess(SuggestionResponse t) {
						// TODO Auto-generated method stub
						listener.getSuggestionSuccess(t);
						
					}
				});
	}
	public static interface SuggestionListener {
		void getSuggestionSuccess(SuggestionResponse t);
		void onFail(HttpResponseException e);

	}
	
}
