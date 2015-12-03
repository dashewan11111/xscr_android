package com.adult.android.model.login;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.adult.android.model.constants.ServiceUrlConstants;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by Administrator on 2015/7/21.
 */
public class QQLoginModel {

    private Activity context;
    public static QQAuth mQQAuth;
    private UserInfo mInfo;
    private Tencent mTencent;

    private MyHandler mHandler = new MyHandler();

    private static final QQLoginModel mQQLoginModel = new QQLoginModel();

    public static QQLoginModel getInstance() {
        return mQQLoginModel;
    }

    /**
     * QQ登陆
     *
     * @param context
     */
    public void login(final Activity context, final OnLoginListener onLoginListener) {
        this.context = context;
        mQQAuth = QQAuth.createInstance(ServiceUrlConstants.QQ_APP_ID, context.getApplicationContext());
        mTencent = Tencent.createInstance(ServiceUrlConstants.QQ_APP_ID, context);
        if (mTencent.isSupportSSOLogin(context)) {
            if (!mQQAuth.isSessionValid()) {
                IUiListener listener = new BaseUiListener() {
                    @Override
                    protected void doComplete(JSONObject values) {
                        Log.e("登陆成功", values.toString());
                        Log.e("22222 Session是否可用？", mQQAuth.isSessionValid() + "");
                        onLoginListener.onLoginSuccess();
                        getUserInfo(onLoginListener);
                    }
                };
                //  mQQAuth.login(context, "all", listener);
                mTencent.login(context, "all", listener);
//            mTencent.loginWithOEM(context, "all", listener, "10000144",
//                    "10000144", "xxxx");
            } else {
                mQQAuth.logout(context);
            }
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo(final OnLoginListener onLoginListener) {
        //if (mQQAuth != null && mQQAuth.isSessionValid()) {
        if (mQQAuth != null) {
            mInfo = new UserInfo(context, mQQAuth.getQQToken());
            mInfo.getUserInfo(new IUiListener() {

                @Override
                public void onComplete(final Object response) {
                    onLoginListener.onGetUserInfoSuccess();
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onError(UiError e) {
                }

                @Override
                public void onCancel() {
                }
            });
        } else {
            Log.e("获取QQ 用户信息", "session 无效");
        }
    }


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
        }

        @Override
        public void onCancel() {
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    JSONObject response = (JSONObject) msg.obj;
                    Log.e("QQ 用户信息", response.toString());
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnLoginListener {
        void onLoginSuccess();

        void onGetUserInfoSuccess();
    }
}
