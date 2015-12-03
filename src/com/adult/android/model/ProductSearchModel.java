package com.adult.android.model;

import android.util.Log;

import com.adult.android.entity.ProductSearchList;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;

//http://api.ccigmall.com/router?v=1.0&cdid=017-010001&b2C=true&method=product.search&appKey=100001
public class ProductSearchModel {
   public void getProductSearch(final ProductSearchListListener listener){
		InputBean inputBean = new InputBean();
		// 初始化请求参数
		inputBean.putQueryParam(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam("cdid","017-010001");
		inputBean.putQueryParam("b2C","true");
		inputBean.putQueryParam("method","product.search");
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,ServiceUrlConstants.APP_KEY_VALUE);

		InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean, ProductSearchList.class,
				new HttpResponseListener<ProductSearchList>() {
					@Override
					public void onStart(){
						Log.i("ws","ProductSearchModel.onStart");
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
					public void onSuccess(ProductSearchList t) {
						// TODO Auto-generated method stub
						listener.onSuccess(t);
						
					}
				});
   }
   
   public static interface ProductSearchListListener {
		void onSuccess(ProductSearchList t);
		void onFail(HttpResponseException e);

	}
}
