package com.adult.android.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.adult.android.R;
import com.adult.android.entity.UserDto;
import com.adult.android.entity.UserResponse;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserModel;
import com.adult.android.model.UserModel.OnLoginCompletedListener;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.utils.CheckCode;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;

public class LoginActivity2 extends BaseActivity implements OnClickListener {

	public static final int FROM_RRODUCT_DETATIL = 11;

	public static final String ACTION_REFRESH_USER = "refresh_user_info";

	private EditText edTxtUserName, edTxtPassword;

	private LoadingDialog loaddingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		loaddingDialog = new LoadingDialog(this);
		setShowTitleBar(true);
		setLeftButtonOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		setActivityTitle(R.string.string_title_login);

		findViewById(R.id.btn_register).setOnClickListener(this);
		findViewById(R.id.login_loginBtn).setOnClickListener(this);

		findViewById(R.id.btn_forget_pwd).setOnClickListener(this);
		edTxtUserName = (EditText) findViewById(R.id.login_usernameEdt);
		edTxtPassword = (EditText) findViewById(R.id.login_psdEdt);

		if (!GeneralTool.isEmpty(UserLogic.getInsatnace().getUserName())) {
			edTxtUserName.setText((UserLogic.getInsatnace().getUserName()));
			edTxtUserName.setSelection((UserLogic.getInsatnace().getUserName().length()));
		}
	}

	// 处理点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 注册按钮
		case R.id.btn_register:

			break;
		// 登录按钮
		case R.id.login_loginBtn:
			login();
			break;
		// 忘记密码
		case R.id.btn_forget_pwd:
			startActivity(new Intent(LoginActivity2.this, FindPsdActivity2.class));
			break;

		default:
			break;
		}
	}

	private void login() {
		final String account = edTxtUserName.getText().toString().trim();
		final String password = edTxtPassword.getText().toString().trim();
		if (GeneralTool.isEmpty(account)) {
			ToastUtil.showToastShort(LoginActivity2.this, R.string.login_nick_name_empty);
			return;
		}
		// if (CheckCode.isChinese(account)) {
		// ToastUtil.showToastShort(LoginActivity2.this,
		// R.string.name_chinese_error);
		// return;
		// }

		if (GeneralTool.isEmpty(password)) {
			ToastUtil.showToastShort(LoginActivity2.this, R.string.string_hint_login_pas);
			return;
		}
		// if (account.length() > 20 || account.length() < 6) {
		// ToastUtil.showToastShort(LoginActivity2.this,
		// R.string.name_lengh_limit);
		// return;
		// }
		// if (name.length() < 6) {
		// ToastUtil.showToastShort(LoginActivity.this, "用户名不能少于6个字符");
		// return;
		// }
		if (password.length() > 20 || password.length() < 6) {
			ToastUtil.showToastShort(LoginActivity2.this, R.string.pwd_lengh_limit);
			return;
		}
		if (CheckCode.isChinese(password)) {
			ToastUtil.showToastShort(LoginActivity2.this, R.string.pwd_chinese_error);
			return;
		}
		// if (name.equals(psd)) {
		// ToastUtil.showToastShort(LoginActivity2.this,
		// R.string.pwd_name_same_error);
		// return;
		// }

		// if (GeneralTool.isSameChars(psd)) {
		// ToastUtil.showToastShort(LoginActivity.this, "密码不能是相同数字或字母，请重新输入");
		// return;
		// }
		loaddingDialog.show();
		UserModel.getInstance().login(account, password, new OnLoginCompletedListener() {

			@Override
			public void onSuccess(UserResponse info) {
				loaddingDialog.dismiss();
				UserDto user = info.getData();
				user.setPassword(password);
				UserLogic.getInsatnace().setUserInfo(user);
				sendBroadcast(new Intent(ACTION_REFRESH_USER));
				finish();
			}

			@Override
			public void onStart() {

			}

			@Override
			public void onHttpException(HttpResponseException e) {
				loaddingDialog.dismiss();
			}

			@Override
			public void onFinish() {
				loaddingDialog.dismiss();
			}

			@Override
			public void onFailed(ResponseException e) {
				loaddingDialog.dismiss();
				ToastUtil.showToastShort(LoginActivity2.this, e.getResultMsg());
				// finish();
			}
		});
	}
}
