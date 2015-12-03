package com.adult.android.presenter.fragment.main.tab.adapter;

/**
 * Created by HANNS on 2015/1/8.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adult.android.R;
import com.adult.android.model.PictureModel;
import com.adult.android.presenter.activity.ImagePagerActivity;
import com.adult.android.utils.Misc;
import com.lidroid.xutils.BitmapUtils;

/**
 * 
 * @ClassName: ProductDetailPicAdapter
 * @Description: 商品图片适配器(适配器，负责装配 、销毁 数据 和 组件 。)
 * @author 潘学坤
 * @date 2015年2月4日 下午2:11:42
 * 
 */
public class ProductDetailPicAdapter extends PagerAdapter {
	private Activity context;
	private LayoutInflater inflater;
	private BitmapUtils bitmapUtils;

	public void setType(String type) {
		this.type = type;
	}

	private String type;
	List<String> dataList = new ArrayList<String>();
	private int[] screenDiaplay;

	public ProductDetailPicAdapter(Activity context, String type) {
		this.context = context;
		this.type = type;
		inflater = LayoutInflater.from(context);
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		screenDiaplay = Misc.getScreenDisplay(context);
	}

	public void setUrls(List<String> urls) {
		dataList.clear();
		if (urls != null && urls.size() > 0) {
			dataList.addAll(urls);
		}
		notifyDataSetChanged();
	}

	public List<String> getDataList() {
		return dataList;
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
		View imageLayout = inflater.inflate(R.layout.item, view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		ViewGroup.LayoutParams rlp = imageView.getLayoutParams();
		if (rlp != null) {
			rlp.width = screenDiaplay[0];
			rlp.height = screenDiaplay[0];
		}
		imageView.setLayoutParams(rlp);
		bitmapUtils
				.display(imageView, PictureModel.DisplayModule.ProductDetail
						.urlWithHost(url, type));
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
				intent.putExtra(ImagePagerActivity.EXTRA_OrderSupplyType, type);
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