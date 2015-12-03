package com.adult.android.model.login;

import com.adult.android.logic.UserLogic;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpSampleResponseListener;
import com.adult.android.presenter.AgentApplication;
import com.tencent.mm.sdk.modelmsg.SendAuth;

/**
 * Created by Administrator on 2015/7/21.
 */
public class WXLoginModel {
    private WXLoginModel() {
    }

    private static final WXLoginModel mWXLoginModel = new WXLoginModel();

    public static WXLoginModel getInstance() {
        return mWXLoginModel;
    }

    public void getCode() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = Math.random() * 10 + UserLogic.getInsatnace().getSession();
        AgentApplication.iwxapi.sendReq(req);
    }

    public void getToken(String code, final OnGetTokenCompletedListener listener) {
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?");
        String appId = ServiceUrlConstants.WECHAT_APPID;
        String secret = ServiceUrlConstants.WECHAT_APPSECRET;
        url.append("appid=");
        url.append(appId);
        url.append("&");
        url.append("secret=");
        url.append(secret);
        url.append("&");
        url.append("code=");
        url.append(code);
        url.append("&");
        url.append("grant_type=authorization_code");
        InputBean bean = new InputBean();
        InternetClient.getSample(url.toString(), bean, String.class,
                new HttpSampleResponseListener<String>() {

                    @Override
                    public void onStart() {
                        listener.onGetTokenStart();
                    }

                    @Override
                    public void onSuccess(String data) {
                        listener.onGetTokenCompleted(data);
                    }

                    @Override
                    public void onHttpException(HttpResponseException e) {
                        listener.onHttpException(e);
                    }

                    @Override
                    public void onBusinessException(BusinessException e) {
                        listener.onGetTokenFailed(e);
                    }

                    @Override
                    public void onOtherException(Throwable throwable) {
                    }

                    @Override
                    public void onFinish() {
                        listener.onGetTokenFinish();
                    }
                });
    }

    public void getUserInfo(String access_token, String open_id, final OnGetUserInfoCompletedListener listener) {
        String url = "https://api.weixin.qq.com/sns/userinfo?";
        url += "access_token=";
        url += access_token;
        url += "&openid=";
        url += open_id;
        InputBean bean = new InputBean();
        InternetClient.getSample(url, bean, String.class,
                new HttpSampleResponseListener<String>() {

                    @Override
                    public void onStart() {
                        listener.onGetUserInfoStart();
                    }

                    @Override
                    public void onSuccess(String product) {
                        listener.onGetUserInfoCompleted(product);
                    }

                    @Override
                    public void onHttpException(HttpResponseException e) {
                        listener.onHttpException(e);
                    }

                    @Override
                    public void onBusinessException(BusinessException e) {
                        listener.onGetUserInfoFailed(e);
                    }

                    @Override
                    public void onOtherException(Throwable throwable) {

                    }

                    @Override
                    public void onFinish() {
                        listener.onGetUserInfoFinish();
                    }
                });
    }

    public interface OnGetTokenCompletedListener {
        void onGetTokenCompleted(String info);

        void onGetTokenFailed(BusinessException e);

        void onHttpException(HttpResponseException e);

        void onGetTokenStart();

        void onGetTokenFinish();
    }

    public interface OnGetUserInfoCompletedListener {
        void onGetUserInfoCompleted(String info);

        void onGetUserInfoFailed(BusinessException e);

        void onHttpException(HttpResponseException e);

        void onGetUserInfoStart();

        void onGetUserInfoFinish();
    }
}
