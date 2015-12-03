package com.adult.android.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.adult.android.R;

public class MarrageStatus extends BaseActivity implements OnClickListener {

	public static final int MARRAGE_STATUS = 111;

	private String marrageStatus = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("婚姻状况");
		setShowRightText("保存");
		setRightTextOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				finish();
				Intent intent = new Intent(LoginActivity2.ACTION_REFRESH_USER);
				intent.putExtra("marrageStatus", marrageStatus);
				sendBroadcast(intent);
			}
		});
		findViewById(R.id.marrage_status_single).setOnClickListener(this);
		findViewById(R.id.marrage_status_in_love).setOnClickListener(this);
		findViewById(R.id.marrage_status_married).setOnClickListener(this);
		findViewById(R.id.marrage_status_divoice).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		findViewById(R.id.marrage_status_single).setBackgroundColor(
				getResources().getColor(R.color.white));
		findViewById(R.id.marrage_status_in_love).setBackgroundColor(
				getResources().getColor(R.color.white));
		findViewById(R.id.marrage_status_married).setBackgroundColor(
				getResources().getColor(R.color.white));
		findViewById(R.id.marrage_status_divoice).setBackgroundColor(
				getResources().getColor(R.color.white));
		switch (view.getId()) {
		case R.id.marrage_status_single:
			marrageStatus = "1";
			findViewById(R.id.marrage_status_single).setBackgroundColor(
					getResources().getColor(R.color.gold));
			break;
		case R.id.marrage_status_in_love:
			marrageStatus = "2";
			findViewById(R.id.marrage_status_in_love).setBackgroundColor(
					getResources().getColor(R.color.gold));
			break;
		case R.id.marrage_status_married:
			marrageStatus = "3";
			findViewById(R.id.marrage_status_married).setBackgroundColor(
					getResources().getColor(R.color.gold));
			break;
		case R.id.marrage_status_divoice:
			marrageStatus = "4";
			findViewById(R.id.marrage_status_divoice).setBackgroundColor(
					getResources().getColor(R.color.gold));
			break;
		default:
			break;
		}

	}
}
