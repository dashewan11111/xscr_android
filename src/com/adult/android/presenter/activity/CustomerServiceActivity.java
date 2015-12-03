package com.adult.android.presenter.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.adult.android.R;
public class CustomerServiceActivity extends BaseActivity implements
		OnClickListener {

	private LinearLayout llytOnline, llytHelp, llytPhone;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_help);

		llytOnline = (LinearLayout) findViewById(R.id.customer_service_online);
		llytHelp = (LinearLayout) findViewById(R.id.customer_service_help_center);
		llytPhone = (LinearLayout) findViewById(R.id.customer_service_phone);

		llytOnline.setOnClickListener(this);
		llytHelp.setOnClickListener(this);
		llytPhone.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.customer_service_online:

			break;
		case R.id.customer_service_help_center:

			break;
		case R.id.customer_service_phone:

			break;

		default:
			break;
		}

	}

}
