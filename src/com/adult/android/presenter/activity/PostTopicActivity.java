package com.adult.android.presenter.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adult.android.R;
import com.adult.android.model.CommunityModel;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.Misc;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;

public class PostTopicActivity extends BaseActivity {

	private EditText edtxtTitle, edtxtContent;

	private LinearLayout llytImages;

	private ImageView img1, img2, img3;

	private List<File> fileList;

	private List<String> fileNameList;

	private LoadingDialog loadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("发帖");
		setShowRightText("提交");
		loadingDialog = new LoadingDialog(this);
		setRightTextOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				loadingDialog.show();
				new GeocodeingTask().execute();
			}
		});
		fileList = new ArrayList<File>();
		edtxtTitle = (EditText) findViewById(R.id.post_topic_title);
		edtxtContent = (EditText) findViewById(R.id.post_topic_content);
		llytImages = (LinearLayout) findViewById(R.id.post_topic_llyt_images);
		img1 = (ImageView) findViewById(R.id.post_topic_image_1);
		img2 = (ImageView) findViewById(R.id.post_topic_image_2);
		img3 = (ImageView) findViewById(R.id.post_topic_image_3);
		img1.setOnClickListener(new OnImageClickListener(1));
		img2.setOnClickListener(new OnImageClickListener(2));
		img3.setOnClickListener(new OnImageClickListener(3));
	}

	class OnImageClickListener implements OnClickListener {

		public OnImageClickListener(int i) {

		}

		@Override
		public void onClick(View view) {
			Intent intent = new Intent();
			/* 开启Pictures画面Type设定为image */
			intent.setType("image/*");
			/* 使用Intent.ACTION_GET_CONTENT这个Action */
			intent.setAction(Intent.ACTION_GET_CONTENT);
			/* 取得相片后返回本画面 */
			startActivityForResult(intent, 1);

		}

	}

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
			// Log.e("文件地址", Misc.getImageAbsolutePath(this, uri));
			fileList.add(new File(Misc.getImageAbsolutePath(this, uri)));
			switch (fileList.size()) {
			case 1:
				img1.setImageBitmap(bitmap);
				break;
			case 2:
				img2.setImageBitmap(bitmap);
				break;
			case 3:
				img3.setImageBitmap(bitmap);
				break;
			default:
				break;
			}
		} catch (FileNotFoundException e) {
			Log.e("Exception", e.getMessage(), e);
		}
	}

	private class GeocodeingTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			postTopic();
			return 0;
		}
	}

	/**
	 * post topic
	 */
	protected void postTopic() {
		String title = edtxtTitle.getText().toString();
		String content = edtxtContent.getText().toString();
		if (GeneralTool.isEmpty(title)) {
			ToastUtil.showToastShort(this, "title is null");
			return;
		}
		if (GeneralTool.isEmpty(content)) {
			ToastUtil.showToastShort(this, "content is null");
			return;
		}

		CommunityModel.getInstance().postTopic(
				AgentApplication.get().getUserInfo().getcUserId(),
				getIntent().getStringExtra("communityId"), title, content,
				fileList, new CommunityModel.OnSuccessListner() {

					@Override
					public void onSuccess(String result) {
						mHandler.sendEmptyMessage(0);
					}

					@Override
					public void onFailed() {

					}

				});
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			loadingDialog.dismiss();
			ToastUtil.showToastShort(PostTopicActivity.this, "帖子发送成功");
			Misc.finishDelay(PostTopicActivity.this);
		};
	};
}
