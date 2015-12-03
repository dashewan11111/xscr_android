/*
 * Have a nice day.
 * @author YangSong
 * @mail song.yang@kuwo.cn
 */
package com.adult.android.view.widget.adapter;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.adult.android.R;
import com.adult.android.entity.HomeFinalItem;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

public class GalleryAdapter extends BaseAdapter {

	private Context context;

	private BitmapUtils bitmapUtils;

	private List<HomeFinalItem> items;

	public GalleryAdapter(Context context, List<HomeFinalItem> items,
			BitmapUtils bitmapUtils) {
		this.items = items;
		this.context = context;
		this.bitmapUtils = bitmapUtils;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public HomeFinalItem getItem(int position) {
		if (items == null || items.size() == 0)
			return null;
		return items.get(position % items.size());
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.recommend_gallery_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.gallery_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		HomeFinalItem item = (HomeFinalItem) getItem(position);
		if (null != item) {
			bitmapUtils
					.configDefaultLoadFailedImage(R.drawable.img_default_720_360);
			bitmapUtils.display(viewHolder.imageView,
					ServiceUrlConstants.getImageHost() + item.getImgUrl());
		}
		return convertView;
	}

	private class ViewHolder {
		public ImageView imageView;
	}
}
