package com.adult.android.presenter.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.SampleDialog;

public class VerifyActivity extends BaseActivity implements OnClickListener {

	private EditText edTxtCode;

	private TextView txtMobile;

	private Button btnReSend, btnVerify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	/** 初始化 */
	private void initView() {
		setShowTitleBar(true);
		setActivityTitle(R.string.string_title_register);
		edTxtCode = (EditText) findViewById(R.id.verify_code);
		txtMobile = (TextView) findViewById(R.id.verify_mobile);

		btnReSend = (Button) findViewById(R.id.verify_btn_resend);
		btnVerify = (Button) findViewById(R.id.verify_btn_verify);
		btnReSend.setEnabled(false);
		btnVerify.setEnabled(false);
		btnReSend.setOnClickListener(this);
		btnVerify.setOnClickListener(this);
		edTxtCode.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence str, int arg1, int arg2, int arg3) {
				if (null != str && 0 < str.length()) {
					btnVerify.setTextColor(getResources().getColor(R.color.white));
					btnVerify.setEnabled(true);
				} else {
					btnVerify.setTextColor(getResources().getColor(R.color.gray));
					btnVerify.setEnabled(false);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});
		txtMobile.setText(getIntent().getStringExtra("mobile"));
		startTime();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.verify_btn_resend:// 重新获取验证码按钮
			startTime();
			break;
		case R.id.verify_btn_verify:// 提交
			verify(edTxtCode.getText().toString());
			break;
		default:
			break;
		}
	}

	Timer timer;
	int currentTime = 60;
	MyHandler mHadHandler = new MyHandler();

	private void startTime() {
		btnReSend.setEnabled(false);
		currentTime = 60;
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				mHadHandler.sendEmptyMessage(0);
			}
		}, 1000, 1000);
	}

	private void stopTime() {
		if (null != timer) {
			timer.cancel();
		}
		btnReSend.setEnabled(true);
	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			currentTime--;
			if (0 < currentTime) {
				btnReSend.setEnabled(false);
				btnReSend.setText("重新发送(" + currentTime + ")");
			} else {
				btnReSend.setEnabled(true);
				btnReSend.setText("重新发送");
			}
		}
	}

	/** 获取验证码 */
	private void verify(String code) {
		if (code.equals(getIntent().getStringExtra("verifyCode"))) {
			Intent intent = new Intent(this, CommitUserProfile.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.putExtra("mobile", getIntent().getStringExtra("mobile"));
			intent.putExtra("verifyCode", getIntent().getStringExtra("verifyCode"));
			startActivity(intent);
			stopTime();
			finish();
		} else {
			ToastUtil.showToastShort(this, "验证码不正确");
		}
	}

	@Override
	public void onBackPressed() {
		final SampleDialog dialog = new SampleDialog(this) {

			@Override
			public View getContentView() {
				return null;
			}
		};
		dialog.setTitleText("提醒", R.color.gray_3);
		dialog.setContentText("确定要退出注册流程吗？", R.color.gray_1);
		dialog.setTwoButton(R.string.ok, R.color.red, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
				dialog.dismiss();
			}
		}, R.string.ok, R.color.red, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				dialog.dismiss();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopTime();
	}
}
