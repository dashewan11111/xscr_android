package com.adult.android.presenter.activity;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.adult.android.utils.CheckCode;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
/**
 * 验证新手机号码
 * 
 * @author liyuj
 * 
 */
public class ValidatePhoneActivity extends BaseActivity implements
		OnClickListener {

	private TextView send_message_tv, sec_tv;
	private Button next_btn, again_get_number_bt;
	private EditText et__vali_number;
	private LoadingDialog lodingDialog;
	private UserInfo user;
	private RelativeLayout rl_sec_tv;
	private Intent intent;
	private String restartFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_validate_phone_number);
		lodingDialog = new LoadingDialog(this);
		user = UserLogic.getInsatnace().getUserBean();
		intent = getIntent();
		
		restartFlag = intent.getStringExtra("restartFlag");
		System.out.println("tempTime>>>>>>>>"+restartFlag);
		
		init();

	}

	/**
	 * 获取验证码请求
	 */
	private void regist() {

		String phone = user.getMobile();
		if (TextUtils.isEmpty(phone)) {
			ToastUtil.showToastLong(this, "手机号码不能为空");
		}
		if (!CheckCode.checkPhone(phone)) {
			
			ToastUtil.showToastLong(this, "请输入有效的手机号码");
		}
		startJishi();
		@SuppressWarnings("unchecked")
		Map<String, String> values = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.userInfoCaptchaGet,
				UserParams.UID, user.getUserId(), UserParams.MB, phone,
				UserParams.SESSIONID, UserLogic.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				values, null);
		UserActionModel.sendGetAction(url, new UserBussListener() {

			@Override
			public void onSuccess(UserAction action, UserBaseInfo baseInfo) {
				lodingDialog.dismiss();
				System.out.println("获取验证码请求");
				ToastUtil.showToastShort(ValidatePhoneActivity.this, baseInfo
						.getResult().getMessage());
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
				ToastUtil.showToastShort(ValidatePhoneActivity.this,
						e.getResultMsg());
			}
		}, UserAction.ACTION_MODIFY_USER_INFO_CODE);
		lodingDialog.show();

	}
	

	/**
	 * 更新计时器
	 */
	@SuppressLint("HandlerLeak")
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

	private void init() {

		send_message_tv = (TextView) findViewById(R.id.send_message_tv);
		sec_tv = (TextView) findViewById(R.id.sec_tv);
		
		
		
		et__vali_number = (EditText) findViewById(R.id.et__vali_number);
		next_btn = (Button) findViewById(R.id.next_btn);
		again_get_number_bt = (Button) findViewById(R.id.again_get_number_bt);
		next_btn.setOnClickListener(this);
		again_get_number_bt.setOnClickListener(this);

		rl_sec_tv = (RelativeLayout) findViewById(R.id.rl_sec_tv);
		
		if (restartFlag.equals("1")) {
			again_get_number_bt.setVisibility(View.GONE);
			rl_sec_tv.setVisibility(View.VISIBLE);
			regist();
			startJishi();
		}else if (restartFlag.equals("0")){
			again_get_number_bt.setVisibility(View.VISIBLE);
			rl_sec_tv.setVisibility(View.GONE);
			
		}else {
			
		}
		
		send_message_tv.setText("我们已经给手机"
				+ user.getMobile().substring(0, 3)
				+ "****"
				+ user.getMobile().substring(user.getMobile().length() - 4,
						user.getMobile().length()) + "发送了一条短信");

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.next_btn:

			String code = et__vali_number.getText().toString();

			if (code != null && !"".equals(code)) {
				UserActionModel.oldmobileValidate(user.getMobile(), code,
						new SampleModelListener<Object>() {

							@Override
							public void onRequestSuccess(Object t) {
								Intent intent = new Intent();
								intent.setClass(ValidatePhoneActivity.this,ValidateNewPhoneActivity.class);
								startActivity(intent);
								finish();
							}

							@Override
							public void onRequestStart() {
								lodingDialog.dismiss();
							}

							@Override
							public void onRequestFinish() {
							}

							@Override
							public void onRequestFail(ResponseException e) {
								lodingDialog.dismiss();
								ToastUtil.showToastShort(ValidatePhoneActivity.this,e.getResultMsg());

							}
						});
			} else {
				ToastUtil.showToastShort(this, "验证码不能为空");
				return;
			}

			break;
		case R.id.again_get_number_bt:

			rl_sec_tv.setVisibility(View.VISIBLE);
			again_get_number_bt.setVisibility(View.GONE);
			startJishi();
			break;

		default:
			break;
		}

	}
}
