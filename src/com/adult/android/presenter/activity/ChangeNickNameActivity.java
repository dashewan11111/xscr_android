package com.adult.android.presenter.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.adult.android.R;
import com.adult.android.entity.UserBaseInfo;
import com.adult.android.entity.UserInfo;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserActionModel;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.constants.SharedPreferencesConstants.FILES;
import com.adult.android.model.constants.SharedPreferencesConstants.PARAMS;
import com.adult.android.model.internet.PostParamsFactory;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.UserBussListener;
import com.adult.android.utils.ActionTool;
import com.adult.android.utils.CheckCode;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.LogUtil;
import com.adult.android.utils.SharedPreferencesUtil;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
import com.adult.android.view.SampleDialog;
public class ChangeNickNameActivity extends BaseActivity implements
		UserBussListener {

	private EditText et_user_nick;
	private LoadingDialog lodingDialog;
	private UserInfo user;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setShowTitleBar(true);
		setActivityTitle("修改昵称");

		setShowRightText("确定");
		setRightTextOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				saveInfo();

			}
		});

		user = UserLogic.getInsatnace().getUserBean();
		intent = getIntent();

		et_user_nick = (EditText) findViewById(R.id.et_user_nick);
		et_user_nick.setText(user.getNickName());
		lodingDialog = new LoadingDialog(this);

	}

	private void saveInfo() {

		String phone = user.getMobile();
		String sex = String.valueOf(user.getGender());
		String bd = intent.getStringExtra("bd");
		String um = user.getUserName();
		String nc = et_user_nick.getText().toString();

		byte[] gbk = null;
		try {
			gbk = nc.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (gbk.length > 20 || gbk.length < 4) {
			ToastUtil.showToastShort(ChangeNickNameActivity.this,
					"用户昵称长度在4-20位之间，单个汉字长度为2");
			return;
		}

		if (GeneralTool.isEmpty(nc)) {
			ToastUtil.showToastShort(ChangeNickNameActivity.this, "昵称不能为空");
			return;
		}

		if (!CheckCode.checkUserName(nc)) {
			ToastUtil.showToastShort(ChangeNickNameActivity.this,
					"昵称格式错误,请重新输入");

			return;
		}

		try {
			nc = URLEncoder.encode(nc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 签名
		InputBean inputBean = PostParamsFactory.createSignInputBean(true,
				UserParams.UM, um, UserParams.MB, phone,
				ServiceUrlConstants.MOTHOD, UserParams.userGetEdit);
		inputBean = PostParamsFactory.createSignInputBean(true, UserParams.UM,
				um, UserParams.MB, phone, ServiceUrlConstants.MOTHOD,
				UserParams.userGetEdit);
		// 添加签名参数
		inputBean.putQueryParam(UserParams.SESSIONID, UserLogic.getInsatnace()
				.getSession());
		inputBean.putQueryParam(UserParams.UM, um);
		inputBean.putQueryParam(UserParams.MB, phone);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams.userGetEdit);
		// 添加不签名的参数
		inputBean.putQueryParam(UserParams.UID, user.getUserId());
		inputBean.putQueryParam(UserParams.NM, nc);

		inputBean.putQueryParam(UserParams.GD, sex);

		if ("请选择生日".equals(bd)) {
			inputBean.putQueryParam(UserParams.BD, "");
		} else {
			inputBean.putQueryParam(UserParams.BD, bd);

		}

		UserActionModel.sendPostAction(this,
				UserAction.ACTION_MODIFY_USER_INFO, inputBean);

		lodingDialog.show();
	}

	@Override
	public void onStartTask() {

	}

	@Override
	public void onSuccess(UserAction action, UserBaseInfo baseInfo) {
		lodingDialog.dismiss();
		updateUI(action, baseInfo);

	}

	@Override
	public void onFaile(UserAction action, BusinessException e) {
		showDialog(e.getResultMsg());
		// ToastUtil.showToastShort(this, e.getResultMsg());

	}

	@Override
	public void onHttpException(UserAction action, HttpResponseException e) {
		lodingDialog.dismiss();

	}

	@Override
	public void onOtherException(UserAction action, Throwable e) {
		lodingDialog.dismiss();

	}

	@Override
	public void onFinishTask() {

	}

	private void showDialog(String content) {
		SampleDialog dialog = new SampleDialog(this) {
			@Override
			public View getContentView() {
				return null;
			}
		};

		dialog.setCanceledOnTouchOutside(false);
		dialog.hideTitle();
		dialog.setContentText(content, 0);
		dialog.setSingleButton("知道了", R.color.bg_title_bar,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						lodingDialog.dismiss();
					}
				});

		dialog.show();
	}

	private void updateUI(UserAction action, UserBaseInfo baseInfo) {
		switch (action) {
		// 修改验证码
		case ACTION_MODIFY_USER_INFO:
			ToastUtil.showToastShort(this, "更新个人信息成功");
			// 执行获取 个人信息
			@SuppressWarnings("unchecked")
			Map<String, String> maps = ActionTool.getActionSignParams(
					UserParams.SESSIONID,
					UserLogic.getInsatnace().getSession(),
					ServiceUrlConstants.MOTHOD, UserParams.userGet);
			String url = ActionTool.getActionUrl(
					ServiceUrlConstants.getApiHost(), maps, null);
			UserActionModel.sendGetAction(url, this,
					UserAction.ACTION_GET_USERINFO);
			lodingDialog.show();
			break;
		// 获取个人信息
		case ACTION_GET_USERINFO:
			LogUtil.Show("SetPersonInfoActivity.updateUI", "获取个人信息成功");
			try {
				UserInfo user = JsonUtils.parse(baseInfo.getData(),
						UserInfo.class);
				UserLogic.getInsatnace().setUserBean(user);
				SharedPreferencesUtil.setSharedPreferences(FILES.FILE_USER,
						PARAMS.USER_INFO, baseInfo.getData());
				SharedPreferencesUtil.setSharedPreferences(FILES.FILE_USER,
						PARAMS.USER_SESSION_ID, UserLogic.getInsatnace()
								.getSession());
				// ShareXmlTool tool = new ShareXmlTool(getActivity(),
				// FILES.FILE_USER);
				// tool.putValue(PARAMS.USER_INFO, baseInfo.getData());
				// tool.putValue(PARAMS.USER_SESSION_ID,
				// UserLogic.getInsatnace()
				// .getSession());
				finish();
			} catch (IOException e) {
				LogUtil.Show("LoginActivity.onSuccess", "解析个人信息 异常");
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
