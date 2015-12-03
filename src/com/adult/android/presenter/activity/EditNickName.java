package com.adult.android.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.adult.android.R;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.ToastUtil;

public class EditNickName extends BaseActivity {

	private EditText edTxtNickName;// 编辑昵称

	private String nickName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("昵称");
		setShowRightText("保存");
		setRightTextOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				String nick = edTxtNickName.getText().toString();
				if (GeneralTool.isEmpty(nick)) {
					ToastUtil.showToastShort(EditNickName.this, "昵称不能为空");
					return;
				}

				ToastUtil.showToastShort(EditNickName.this, "保存");
				finish();
				Intent intent = new Intent(LoginActivity2.ACTION_REFRESH_USER);
				intent.putExtra("nickName", nick);
				sendBroadcast(intent);
			}
		});
		// String nickName =
		// AgentApplication.get().getUserInfo().getLoginName();
		edTxtNickName = (EditText) findViewById(R.id.edit_nick_name);
	}
}
