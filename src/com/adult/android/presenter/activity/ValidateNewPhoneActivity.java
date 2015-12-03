package com.adult.android.presenter.activity;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.UserBaseInfo;
import com.adult.android.entity.UserInfo;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserActionModel;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.UserBussListener;
import com.adult.android.model.listener.SampleModelListener;
import com.adult.android.utils.ActionTool;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
public class ValidateNewPhoneActivity extends BaseActivity implements
		OnClickListener {

	private Button again_get_number_bt, save_bt;

	private RelativeLayout rl_sec_tv;

	private TextView sec_tv;
	private UserInfo user;

	private EditText et_phone_number, et_vali_number;
	private LoadingDialog lodingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		user = UserLogic.getInsatnace().getUserBean();
		lodingDialog = new LoadingDialog(this);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_validate_phone_number);

		init();
	}

	private void init() {

		again_get_number_bt = (Button) findViewById(R.id.again_get_number_bt);
		sec_tv = (TextView) findViewById(R.id.sec_tv);
		rl_sec_tv = (RelativeLayout) findViewById(R.id.rl_sec_tv);
		save_bt = (Button) findViewById(R.id.save_bt);
		save_bt.setOnClickListener(this);
		again_get_number_bt.setOnClickListener(this);
		et_phone_number = (EditText) findViewById(R.id.et_phone_number);
		et_vali_number = (EditText) findViewById(R.id.et_vali_number);
	}

	/**
	 * 更新计时器
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String vaString = sec_tv.getText().toString();
			int sec = Integer.parseInt(vaString);
			sec--;
			if (sec <= 0) {
				stopJishi();
				again_get_number_bt.setVisibility(View.VISIBLE);
				rl_sec_tv.setVisibility(View.GONE);

			} else {
				sec_tv.setText(String.valueOf(sec));

			}
		};
	};

	Timer timer;

	/**
	 * 开始计时
	 */
	private void startJishi() {
		timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		};
		timer.schedule(tt, 1000, 1000);
	}

	/**
	 * 结束计时
	 */
	public void stopJishi() {
		if (timer != null) {
			timer.cancel();
			sec_tv.setText("120");
		}
		timer = null;
	}

	@Override
	public void onClick(View arg0) {

		String phone = et_phone_number.getText().toString();
		
		if ("".equals(phone)|| phone == null) {
			ToastUtil.showToastShort(this, "手机号码不能为空");
			return;
		}
		
		

		switch (arg0.getId()) {
		case R.id.save_bt:

			String vd = et_vali_number.getText().toString();
			/**
			 * 保存修改内容
			 */
			UserActionModel.newmobileValidate(phone, vd,
					new SampleModelListener<Object>() {

						@Override
						public void onRequestSuccess(Object t) {
							finish();
						}

						@Override
						public void onRequestStart() {
							lodingDialog.show();
						}

						@Override
						public void onRequestFinish() {
							lodingDialog.dismiss();
						}

						@Override
						public void onRequestFail(ResponseException e) {
							ToastUtil.showToastShort(
									ValidateNewPhoneActivity.this,
									e.getResultMsg());
						}
					});

			break;

		case R.id.again_get_number_bt:
			again_get_number_bt.setVisibility(View.GONE);
			rl_sec_tv.setVisibility(View.VISIBLE);
			startJishi();
			sendGetValidateNumber(phone);

			break;

		default:
			break;
		}

	}

	/**
	 * 获取验证码请求
	 * 
	 * @param p
	 */
	private void sendGetValidateNumber(String p) {
		Map<String, String> values = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.userInfoCaptchaGet,
				UserParams.UID, user.getUserId(), UserParams.MB, p,
				UserParams.SESSIONID, UserLogic.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				values, null);
		UserActionModel.sendGetAction(url, new UserBussListener() {

			@Override
			public void onSuccess(UserAction action, UserBaseInfo baseInfo) {
				lodingDialog.dismiss();
				ToastUtil.showToastShort(ValidateNewPhoneActivity.this,
						baseInfo.getResult().getMessage());
			}

			@Override
			public void onStartTask() {

			}

			@Override
			public void onOtherException(UserAction action, Throwable e) {
				lodingDialog.dismiss();

			}

			@Override
			public void onHttpException(UserAction action,
					HttpResponseException e) {
				lodingDialog.dismiss();

			}

			@Override
			public void onFinishTask() {

			}

			@Override
			public void onFaile(UserAction action, BusinessException e) {
				lodingDialog.dismiss();
				ToastUtil.showToastShort(ValidateNewPhoneActivity.this,
						e.getResultMsg());
			}
		}, UserAction.ACTION_MODIFY_USER_INFO_CODE);

	}

}
