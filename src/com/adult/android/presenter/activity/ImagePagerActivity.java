package com.adult.android.presenter.activity;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.view.touchgallery.GalleryWidget.GalleryViewPager;
import com.adult.android.view.touchgallery.GalleryWidget.UrlPagerAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * 图片查看器
 */
public class ImagePagerActivity extends BaseActivity {
	private static final String STATE_POSITION = "STATE_POSITION";
	public static final String EXTRA_IMAGE_INDEX = "image_index";
	public static final String EXTRA_IMAGE_URLS = "image_urls";
	public static final String EXTRA_OrderSupplyType = "OrderSupplyType";

	private String type;
	private GalleryViewPager mPager;
	private int pagerPosition;
	private TextView indicator;
	Bitmap defaultbmp;
	DisplayImageOptions options;// jar包声明配置
	protected ImageLoader imageLoader = ImageLoader.getInstance();// jar包声明图片类

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		defaultbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.img_default_114);
		imageLoader.init(ImageLoaderConfiguration
				.createDefault(ImagePagerActivity.this));

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.img_default_114)
				.showImageOnFail(R.drawable.img_default_114)
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.ARGB_8888)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
		type = (String) getIntent().getSerializableExtra(EXTRA_OrderSupplyType);
		ArrayList<String> urls = (ArrayList<String>) getIntent()
				.getSerializableExtra(EXTRA_IMAGE_URLS);
		mPager = (GalleryViewPager) findViewById(R.id.viewer);
		UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, urls, type);
		mPager.setOffscreenPageLimit(3);
		mPager.setAdapter(pagerAdapter);
		mPager.setOnItemClickListener(new GalleryViewPager.OnItemClickListener() {
			@Override
			public void onItemClicked(View view, int position) {
				finish();
			}
		});
		indicator = (TextView) findViewById(R.id.indicator);

		CharSequence text = getString(R.string.viewpager_indicator, 1, mPager
				.getAdapter().getCount());
		indicator.setText(text);
		// 更新下标
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				CharSequence text = getString(R.string.viewpager_indicator,
						arg0 + 1, mPager.getAdapter().getCount());
				indicator.setText(text);
			}

		});
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		mPager.setCurrentItem(pagerPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, mPager.getCurrentItem());
	}
}
