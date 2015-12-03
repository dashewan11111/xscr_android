package com.adult.android.model;

import android.text.TextUtils;

import com.adult.android.R;
import com.adult.android.entity.CategoryListResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.presenter.AgentApplication;

/**
 * Created by huangchao on 8/12/2015.
 *
 * 商品搜索列表
 */
public class ProductSortModel {
    /**
     * 获取商品搜索列表和搜索条件
     * @param sortType 排序类型
     * @param cdid 分类id
     * @param cyid 原产地id
     * @param priceRange 价格区间
     * @param page 页码
     * @param listener
     * @param isCreate true表示刚进入页面需要保存搜索条件。 false表示选择了搜索条件或者上下拉listView不需要保存搜索条件
     */
    public void getProductSort(String sortType, String cdid, String brandName, String countryName, String cyid, String priceRange, int page, final ProductSortListener listener, final boolean isCreate) {
        InputBean inputBean = new InputBean();
        inputBean.setCacheable(true);
        inputBean.putQueryParam(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
        inputBean.putQueryParam(ServiceUrlConstants.MOTHOD, ServiceUrlConstants.UserParams.searchlist);
        inputBean.putQueryParam(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
        inputBean.putQueryParam("b2C", "true");
        if(!TextUtils.isEmpty(sortType)){
            inputBean.putQueryParam("sortType",sortType);
        }
        if(!TextUtils.isEmpty(cdid)){
            inputBean.putQueryParam("cdid",cdid);
        }

        if(!TextUtils.isEmpty(brandName)){
            inputBean.putQueryParam("brandName",brandName);
        }

        if(!TextUtils.isEmpty(countryName)){
            inputBean.putQueryParam("countryName",countryName);
        }

        if(!TextUtils.isEmpty(cyid)){
            inputBean.putQueryParam("cyid",cyid);
        }
        if(!TextUtils.isEmpty(priceRange)){
            inputBean.putQueryParam("priceRange",priceRange);
        }
        if (page > 1) {
            inputBean.putQueryParam("pageno", page);
        }
        inputBean.putQueryParam("pageSize", "10");

        InternetClient.get(ServiceUrlConstants.getApiHost(), inputBean,
                CategoryListResponse.class,
                new HttpResponseListener<CategoryListResponse>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onHttpException(HttpResponseException e) {
                        listener.getProductSortFail(e);
                    }

                    @Override
                    public void onBusinessException(BusinessException e) {
                        listener.getProductSortFail(e);
                    }

                    @Override
                    public void onOtherException(Throwable throwable) {
                        ResponseException re = new ResponseException();
						re.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
                        listener.getProductSortFail(re);
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onSuccess(CategoryListResponse t) {
                        listener.getProductSortSuccess(t, isCreate);
                    }
                });

    }

    public static interface ProductSortListener {
        void getProductSortSuccess(CategoryListResponse t, boolean isCreate);

        void getProductSortFail(ResponseException e);
    }
}
