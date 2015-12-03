package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CategoryFrist;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

public class CategoryContentAdapter extends BaseAdapter {

	private Context context;

	private BitmapUtils bitmapUtils;

	private List<CategoryFrist> categorylist;

	public CategoryContentAdapter(Context context,
			List<CategoryFrist> categorylist) {
		this.context = context;
		this.categorylist = categorylist;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
	}

	@Override
	public int getCount() {
		return null == categorylist ? 0 : categorylist.size();
	}

	@Override
	public Object getItem(int position) {
		return categorylist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_category_content, null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.img_category);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.txt_category_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CategoryFrist category = (CategoryFrist) getItem(position);
		holder.txtName.setText(category.getCategoryName());
		bitmapUtils.display(holder.image, ServiceUrlConstants.getImageHost()
				+ category.getImgUrl());
		return convertView;
	}

	private class ViewHolder {
		private ImageView image;
		private TextView txtName;
	}

}
