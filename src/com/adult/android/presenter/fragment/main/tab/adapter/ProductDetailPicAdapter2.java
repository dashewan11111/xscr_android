package com.adult.android.presenter.fragment.main.tab.adapter;

/**
 * Created by HANNS on 2015/1/8.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.activity.ImagePagerActivity;
import com.lidroid.xutils.BitmapUtils;

/**
 *
 */
public class ProductDetailPicAdapter2 extends PagerAdapter {

	private Context context;

	private BitmapUtils bitmapUtils;

	List<String> dataList = new ArrayList<String>();

	public ProductDetailPicAdapter2(Activity context, List<String> dataList) {
		this.context = context;
		this.dataList = dataList;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return this.dataList == null ? 0 : this.dataList.size();
	}

	@Override
	public Object instantiateItem(ViewGroup view, final int position) {
		final String url = dataList.get(position);
		View imageLayout = LayoutInflater.from(context).inflate(R.layout.item,
				view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		bitmapUtils
				.display(imageView, ServiceUrlConstants.getImageHost() + url);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(context, ImagePagerActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
						(Serializable) dataList);
				intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
				intent.putExtra(ImagePagerActivity.EXTRA_OrderSupplyType, "1");
				context.startActivity(intent);
			}
		});
		view.addView(imageLayout, 0);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}