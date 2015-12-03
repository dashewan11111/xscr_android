package com.adult.android.wxapi;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.adult.android.R;
import com.adult.android.entity.WXToken;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.login.WXLoginModel;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.ToastUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final int GET_TOKEN_SUCCESS = 1;

	private static final int GET_TOKEN_FAILD = -1;

	private static final int GET_USERINFO_SUCCESS = 2;

	private static final int GET_USERINFO__FAILD = -2;

	private MyHandler handler = new MyHandler();

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		IWXAPI api = WXAPIFactory.createWXAPI(this,
				ServiceUrlConstants.WECHAT_APPID);
		api.handleIntent(getIntent(), this);
	}

	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) {

	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {
		int result = 0;

		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
				result = R.string.share_success;
				break;
			}
			if (ConstantsAPI.COMMAND_SENDAUTH == resp.getType()) {
				String code = ((SendAuth.Resp) resp).code;
				// Log.e("微信确认登录返回的code：", "resultUrl : " + ((SendAuth.Resp)
				// resp).resultUrl + " , state : " +
				// ((SendAuth.Resp) resp).state + " , token : " +
				// ((SendAuth.Resp) resp).token + " , userName : " +
				// ((SendAuth.Resp) resp).userName + " , expireDate : " +
				// ((SendAuth.Resp) resp).expireDate);
				result = R.string.login_wx_success;
				Message msg = new Message();
				msg.what = 0;
				msg.obj = code;
				if (null == handler) {
					handler = new MyHandler();
				}
				handler.sendMessage(msg);
				break;
			}
			if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.app_tip);
				builder.setMessage(getString(R.string.pay_result_callback_msg,
						resp.errStr + ";code=" + String.valueOf(resp.errCode)));
				builder.show();
				break;
			}

			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
				result = R.string.share_cancel;
				break;
			}
			if (ConstantsAPI.COMMAND_SENDAUTH == resp.getType()) {
				result = R.string.login_wx_cancel;
				break;
			}
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			// 类型 2 ：微信分享； 1 ：微信登陆
			if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
				result = R.string.share_error;
				break;
			}
			if (ConstantsAPI.COMMAND_SENDAUTH == resp.getType()) {
				result = R.string.login_wx_error;
				break;
			}
		case BaseResp.ErrCode.ERR_COMM:
		default:
			result = R.string.share_cancel;
			break;
		}
		ToastUtil.showToastShort(this, result);
		this.finish();
	}

	public class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				getWXToken((String) (msg.obj));
				break;
			case 1:
				WXToken wxToken = (WXToken) msg.obj;
				getWXUserInfo(wxToken);
				break;
			case 2:

				break;
			case -1:

				break;
			case -2:

				break;
			default:
				break;
			}
		}
	}

	/**
	 * 获取微信token
	 */
	private void getWXToken(String code) {
		WXLoginModel.getInstance().getToken(code,
				new WXLoginModel.OnGetTokenCompletedListener() {

					@Override
					public void onGetTokenStart() {
						Log.e("onGetTokenStart:", "onGetTokenStart");
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						Log.e("onHttpException:", "onHttpException");
					}

					@Override
					public void onGetTokenCompleted(String info) {
						Log.e("onGetTokenCompleted:", info);
						WXToken wxToken;
						try {
							wxToken = JsonUtils.parse(info, WXToken.class);
						} catch (IOException e) {
							Log.e("JSON解析失败", "JSON解析失败");
							e.printStackTrace();
							return;
						}
						Log.e("JSON解析成功",
								"getAccess_token : "
										+ wxToken.getAccess_token()
										+ " , getOpenid : "
										+ wxToken.getOpenid());
						Message msg = new Message();
						msg.what = GET_TOKEN_SUCCESS;
						msg.obj = wxToken;
						if (null == handler) {
							handler = new MyHandler();
						}
						handler.sendMessage(msg);
					}

					@Override
					public void onGetTokenFailed(BusinessException e) {
						Log.e("onGetTokenFailed:", "onGetTokenFailed");
					}

					@Override
					public void onGetTokenFinish() {
						Log.e("onGetTokenFinish:", "onGetTokenFinish");
					}
				});
	}

	/**
	 * 获取微信用户信息
	 */
	private void getWXUserInfo(WXToken wxToken) {
		if (null == wxToken) {
			return;
		}
		WXLoginModel.getInstance().getUserInfo(wxToken.getAccess_token(),
				wxToken.getOpenid(),
				new WXLoginModel.OnGetUserInfoCompletedListener() {

					@Override
					public void onHttpException(HttpResponseException e) {
						Log.e("onHttpException:", "onHttpException");
					}

					@Override
					public void onGetUserInfoCompleted(String info) {
						Log.e("onGetUserInfoCompleted:", info);
					}

					@Override
					public void onGetUserInfoFailed(BusinessException e) {
						Log.e("onGetUserInfoFailed:", "onGetUserInfoFailed");
					}

					@Override
					public void onGetUserInfoFinish() {
						Log.e("onGetUserInfoFinish:", "onGetUserInfoFinish");
					}

					@Override
					public void onGetUserInfoStart() {
						Log.e("onGetUserInfoStart:", "onGetUserInfoStart");
					}
				});
	}

}