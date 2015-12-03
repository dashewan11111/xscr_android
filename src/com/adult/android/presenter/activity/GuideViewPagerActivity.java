package com.adult.android.presenter.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adult.android.R;
import com.adult.android.model.constants.SharedPreferencesConstants;
import com.adult.android.utils.SharedPreferencesUtil;
/**
 * User: liyuj<liyuj@cigmall.cn> Date: 2015-08-21 Time: 20:42 FIXME
 */
public class GuideViewPagerActivity extends BaseActivity {
	private ViewPager vp;
	ArrayList<ImageView> imageSource = null;// 图片资源
	int[] images = null;// 图片资源ID
	MyPagerAdapter adapter;// viewPager的适配器
	private String nowVersion;
	private MyHandler mHandler;
	public static final int START_ANI = 0;
	public static final int END_ANI = 1;
	private ImageView welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * //设置通知栏透明 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		 * getWindow
		 * ().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); }
		 */
		setContentView(R.layout.welcome);
		welcome = (ImageView) findViewById(R.id.iv_welcome);
		vp = (ViewPager) this.findViewById(R.id.viewpager);
		mHandler = new MyHandler();
		new LooperThread().start();
	}

	private void initViewPage() {
		vp.setVisibility(View.VISIBLE);
		welcome.setVisibility(View.GONE);
		imageSource = new ArrayList<ImageView>();
		images = new int[] { R.drawable.wel1, R.drawable.wel2, R.drawable.wel3,
				R.drawable.wel4 };

		for (int i = 0; i < images.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(images[i]);
			imageSource.add(image);
		}
		adapter = new MyPagerAdapter();
		vp.setAdapter(adapter);
	}

	/**
	 * 开启欢迎页
	 */
	private void startWelcomePage() {
		String spVersion = SharedPreferencesUtil.getSharedPreferences(
				SharedPreferencesConstants.FILES.FILE_SETTING,
				SharedPreferencesConstants.PARAMS.SDK_VERSION, "");
		try {
			nowVersion = getPackageManager().getPackageInfo(
					GuideViewPagerActivity.this.getPackageName(), 0).versionName
					.toString();
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		if (spVersion == null || "".equals(spVersion)) {
			SharedPreferencesUtil.setSharedPreferences(
					SharedPreferencesConstants.FILES.FILE_SETTING,
					SharedPreferencesConstants.PARAMS.SDK_VERSION, nowVersion);
			initViewPage();
		} else if (spVersion != null && !spVersion.equals(nowVersion)) {
			SharedPreferencesUtil.setSharedPreferences(
					SharedPreferencesConstants.FILES.FILE_SETTING,
					SharedPreferencesConstants.PARAMS.SDK_VERSION, nowVersion);
			initViewPage();
		} else {

			Intent intent = new Intent(GuideViewPagerActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// 判断将要显示的图片是否和现在显示的图片是同一个
			// arg0为当前显示的图片，arg1是将要显示的图片
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 销毁该图片
			container.removeView(imageSource.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 初始化将要显示的图片，将该图片加入到container中，即viewPager中
			container.addView(imageSource.get(position));
			if (images.length - 1 == position) {
				imageSource.get(position).setOnClickListener(
						new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Intent intent = new Intent(
										GuideViewPagerActivity.this,
										MainActivity.class);
								startActivity(intent);
								finish();

							}
						});
			}
			return imageSource.get(position);
		}
	}

	private class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case START_ANI:
				Message msg2 = new Message();
				msg2.what = END_ANI;
				mHandler.sendMessageDelayed(msg2, 3000);
				break;
			case END_ANI:
				startWelcomePage();

				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeMessages(END_ANI);
	}

	/**
	 * 启动欢迎页
	 */
	private class LooperThread extends Thread {
		@Override
		public void run() {
			Looper.prepare();
			Message msg = mHandler.obtainMessage();
			msg.what = START_ANI;
			mHandler.sendMessage(msg);
			Looper.loop();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
