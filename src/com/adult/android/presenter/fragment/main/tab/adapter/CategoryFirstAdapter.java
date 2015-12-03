package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CategoryFrist;
import com.lidroid.xutils.BitmapUtils;

public class CategoryFirstAdapter extends BaseAdapter {

	private Context context;

	private List<CategoryFrist> items;

	private BitmapUtils bitmapUtils;

	public CategoryFirstAdapter(Context context,
			List<CategoryFrist> categorylist) {
		this.context = context;
		items = categorylist;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.category_first_level, null);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.category_first_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CategoryFrist categoryDto = (CategoryFrist) getItem(position);
		holder.txtTitle.setText(categoryDto.getCategoryName());
		if (0 == position) {
			convertView
					.setBackgroundResource(R.drawable.category_list_bg_white);
		}
		return convertView;
	}

	private class ViewHolder {

		private TextView txtTitle;

	}
}
