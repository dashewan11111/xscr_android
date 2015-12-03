package com.adult.android.presenter.activity;

import java.util.Map;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.UserBaseInfo;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserActionModel;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.UserBussListener;
import com.adult.android.utils.ActionTool;
import com.adult.android.utils.CheckCode;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;

/**
 * 
 * @author GongXun
 * 
 * @2015年3月13日
 * 
 * @descripte
 * 
 *            意见反馈
 */
public class FeedbackActivity extends BaseActivity implements OnClickListener,
		UserBussListener {
	private TextView tv_type;
	private String fun[] = new String[] { "功能意见", "操作意见", "界面意见", "您的新需求" };
	ListView lv_type;
	myAdapter adapter = new myAdapter();
	ImageView tempImageView;
	AlertDialog dialog;
	private EditText contentEdt, contectEdt;
	private int indexType = 7;
	private LoadingDialog loadDing;

	private int currentPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 8.意见反馈
		// ((TextView) findViewById(R.id.text_title_feedback))
		// .setText(R.string.string_feedback);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_feedback);
		tv_type = (TextView) findViewById(R.id.tv_sex_value);
		tv_type.setText(fun[0]);
		findViewById(R.id.llyt_feed_back_type).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						initDialog();
					}
				});
		loadDing = new LoadingDialog(this);
		findViewById(R.id.feedBackBtn).setOnClickListener(this);
		contectEdt = (EditText) findViewById(R.id.feedback_contectEdt);
		contentEdt = (EditText) findViewById(R.id.feedback_contentEdt);
	}

	protected void initDialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(FeedbackActivity.this);
		dialog = ad.create();
		Window window = dialog.getWindow();
		dialog.show();
		window.setContentView(R.layout.feedback_layout);
		lv_type = (ListView) window.findViewById(R.id.lv_content_type);
		lv_type.setOnItemClickListener(new myOnItemClickListener());
		lv_type.setAdapter(adapter);
		loadDing = new LoadingDialog(this);
	}

	class myOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			currentPosition = position;
			if (tempImageView != null) {
				tempImageView.setBackgroundDrawable(null);

				tempImageView = null;
			}
			ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
			iv.setBackgroundResource(R.drawable.check_xhdpi);
			tempImageView = iv;
			// 设置value，关闭对话框
			indexType += position;
			tv_type.setText(fun[position]);
			// SystemClock.sleep(500);
			dialog.dismiss();
		}

	}

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return fun.length;
		}

		@Override
		public Object getItem(int position) {
			return fun[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = View.inflate(FeedbackActivity.this,
					R.layout.feedback_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tv_type);
			ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
			tv.setText(fun[position]);
			iv.setBackgroundResource(0);
			if (currentPosition == position) {
				iv.setVisibility(View.VISIBLE);
			} else {
				iv.setVisibility(View.INVISIBLE);
			}
			return view;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.feedBackBtn:
			String contect = contectEdt.getText().toString();
			String content = contentEdt.getText().toString();
			if (GeneralTool.isEmpty(content)) {
				ToastUtil.showToastShort(FeedbackActivity.this, "反馈内容不能为空");
				break;
			}
			if (content.length() > 500) {
				ToastUtil.showToastShort(FeedbackActivity.this, "反馈内容1~500");
				break;
			}
			if (GeneralTool.isEmpty(contect)) {
				ToastUtil.showToastShort(FeedbackActivity.this, "联系方式不能为空");
				break;
			}
			// 去除输入内容中的空格和换行
			String s = content.replaceAll("\r", "").replace("\n", "");

			// 只允许输入手机号和邮箱两种格式
			if (contect.length() == 11 && CheckCode.checkPhone(contect)
					&& !contect.contains("@")) {

				Map<String, String> maps = ActionTool.getActionSignParams(
						ServiceUrlConstants.MOTHOD, UserParams.feedbackAdd);
				String url = ActionTool.getActionUrl(UserParams.getApiHost(),
						maps, UserParams.SESSIONID, UserLogic.getInsatnace()
								.getSession(), UserParams.WAY, contect,
						UserParams.CONTENT, s, UserParams.TYPE, String
								.valueOf(indexType), UserParams.LEVEL, "2");
				UserActionModel.sendGetAction(url, this,
						UserAction.ACTION_COMMON);
			} else if (contect.contains("@") && CheckCode.checkEmail(contect)) {

				Map<String, String> maps = ActionTool.getActionSignParams(
						ServiceUrlConstants.MOTHOD, UserParams.feedbackAdd);
				String url = ActionTool.getActionUrl(UserParams.getApiHost(),
						maps, UserParams.SESSIONID, UserLogic.getInsatnace()
								.getSession(), UserParams.WAY, contect,
						UserParams.CONTENT, s, UserParams.TYPE, String
								.valueOf(indexType), UserParams.LEVEL, "2");
				UserActionModel.sendGetAction(url, this,
						UserAction.ACTION_COMMON);
				loadDing.show();
			} else {
				ToastUtil.showToastShort(FeedbackActivity.this,
						"手机号或邮箱格式错误，请重新输入");
			}

			break;
		default:
			break;
		}
	}

	@Override
	public void onSuccess(UserAction action, UserBaseInfo baseInfo) {
		loadDing.dismiss();
		ToastUtil.showToastShort(FeedbackActivity.this, "反馈成功");
		finish();
	}

	@Override
	public void onFaile(UserAction action, BusinessException e) {
		loadDing.dismiss();
		ToastUtil.showToastShort(FeedbackActivity.this, e.getResultMsg());
	}

	@Override
	public void onHttpException(UserAction action, HttpResponseException e) {
		loadDing.dismiss();
		showExceptionView();
	}

	@Override
	public void onOtherException(UserAction action, Throwable e) {
		loadDing.dismiss();
		showExceptionView();
	}

	@Override
	public void onStartTask() {

	}

	@Override
	public void onFinishTask() {

	}

}
