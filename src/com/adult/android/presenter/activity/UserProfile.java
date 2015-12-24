package com.adult.android.presenter.activity;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.UserDto;
import com.adult.android.entity.UserResponse;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserModel;
import com.adult.android.model.UserModel.OnLoginCompletedListener;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.Misc;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;

public class UserProfile extends BaseActivity implements OnClickListener {

	private LinearLayout llytAvator, llytNick, llytAcouSafty, llytGender,
			llytAge, llytLove, llytMarrage, llytLocation, llytLevel;

	private TextView txtNickName, txtAge, txtSex, txtLove, txtMarriage,
			txtLocation, txtLevel;

	private ImageView imgAvator;

	private LoadingDialog loaddingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerReceiver(broadCastReceiver, new IntentFilter(
				LoginActivity2.ACTION_REFRESH_USER));
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("个人资料");
		loaddingDialog = new LoadingDialog(this);
		llytAvator = (LinearLayout) findViewById(R.id.user_profile_avator);
		llytNick = (LinearLayout) findViewById(R.id.user_profile_nick);
		llytAcouSafty = (LinearLayout) findViewById(R.id.user_profile_acount_safe);
		llytGender = (LinearLayout) findViewById(R.id.user_profile_sexy);
		llytAge = (LinearLayout) findViewById(R.id.user_profile_age);
		llytLove = (LinearLayout) findViewById(R.id.user_profile_love);
		llytMarrage = (LinearLayout) findViewById(R.id.user_profile_marry);
		llytLocation = (LinearLayout) findViewById(R.id.user_profile_location);
		llytLevel = (LinearLayout) findViewById(R.id.user_profile_level);

		imgAvator = (ImageView) findViewById(R.id.user_profile_img_avator);
		txtNickName = (TextView) findViewById(R.id.user_profile_nick_txt);
		txtAge = (TextView) findViewById(R.id.user_profile_age_txt);
		txtSex = (TextView) findViewById(R.id.user_profile_sexy_txt);
		txtLove = (TextView) findViewById(R.id.user_profile_love_txt);
		txtMarriage = (TextView) findViewById(R.id.user_profile_marry_txt);
		txtLocation = (TextView) findViewById(R.id.user_profile_location_txt);
		txtLevel = (TextView) findViewById(R.id.user_profile_level_txt);

		llytAvator.setOnClickListener(this);
		llytNick.setOnClickListener(this);
		llytAcouSafty.setOnClickListener(this);
		llytGender.setOnClickListener(this);
		llytAge.setOnClickListener(this);
		llytLove.setOnClickListener(this);
		llytMarrage.setOnClickListener(this);
		llytLocation.setOnClickListener(this);
		llytLevel.setOnClickListener(this);

		getUserInfo();
	}

	/** 获取用户信息 */
	private void getUserInfo() {
		loaddingDialog.show();
		UserModel.getInstance().getUserInfo(new OnLoginCompletedListener() {

			@Override
			public void onSuccess(UserResponse info) {
				loaddingDialog.dismiss();
				UserDto user = info.getData();
				user.setPassword(AgentApplication.get().getUserInfo()
						.getPassword());
				UserLogic.getInsatnace().setUserInfo(user);
				txtNickName.setText(user.getLoginName());
				txtAge.setText(GeneralTool.isEmpty(user.getBirthday()) ? "待完善"
						: user.getBirthday());
				txtSex.setText(GeneralTool.isEmpty(user.getSex()) ? "待完善"
						: ("1".equals(user.getSex()) ? "男" : "女"));
				txtLove.setText(GeneralTool.isEmpty(user.getSexualOrientation()) ? "待完善"
						: user.getSexualOrientation());
				txtMarriage.setText(GeneralTool.isEmpty(user
						.getMarriageStatus()) ? "待完善" : user
						.getMarriageStatus());
				txtLocation.setText(GeneralTool.isEmpty(user.getProvince()) ? "待完善"
						: user.getProvince());
				txtLevel.setText(GeneralTool.isEmpty(user.getvLevel()) ? "待完善"
						: user.getvLevel() + "级");

				// sendBroadcast(new
				// Intent(LoginActivity2.ACTION_REFRESH_USER));
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
				ToastUtil.showToastShort(UserProfile.this, e.getResultMsg());
			}
		});

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.user_profile_avator:
			Intent intent = new Intent();
			/* 开启Pictures画面Type设定为image */
			intent.setType("image/*");
			/* 使用Intent.ACTION_GET_CONTENT这个Action */
			intent.setAction(Intent.ACTION_GET_CONTENT);
			/* 取得相片后返回本画面 */
			startActivityForResult(intent, 1);
			break;
		case R.id.user_profile_nick:
			Intent intentNick = new Intent(this, EditNickName.class);
			intentNick.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentNick);
			break;
		case R.id.user_profile_acount_safe:
			Intent intentAccountSafe = new Intent(this,
					AccountSafeActivity.class);
			intentAccountSafe.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentAccountSafe);
			break;
		case R.id.user_profile_sexy:
			// Intent intentGender = new Intent(this, EditNickName.class);
			// intentGender.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
			// | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			// startActivity(intentGender);
			break;
		case R.id.user_profile_age:
			Intent intentGender = new Intent(this, EditAgeActivity.class);
			intentGender.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentGender);
			break;
		case R.id.user_profile_love:
			Intent intentLove = new Intent(this, SexLoveActivity.class);
			intentLove.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentLove);
			break;
		case R.id.user_profile_marry:
			Intent intentMarrage = new Intent(this, MarrageStatus.class);
			intentMarrage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentMarrage);
			break;
		case R.id.user_profile_location:
			Intent intentLocation = new Intent(this, EditNickName.class);
			intentLocation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentLocation);
			break;
		case R.id.user_profile_level:
			Intent intentLevel = new Intent(this, EditNickName.class);
			intentLevel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intentLevel);
			break;
		default:
			break;
		}
	}

	File imageFile;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Uri uri = data.getData();
			addImageView(uri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void addImageView(Uri uri) {
		ContentResolver cr = this.getContentResolver();
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
			imageFile = new File(Misc.getImageAbsolutePath(this, uri));
			imgAvator.setImageBitmap(bitmap);
			new GeocodeingTask().execute();
		} catch (FileNotFoundException e) {
			Log.e("Exception", e.getMessage(), e);
		}
	}

	private class GeocodeingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			updateAvator();
			return 0;
		}
	}

	public void updateAvator() {

	}

	BroadcastReceiver broadCastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

		}
	};

}
