package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CategorySort;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

/**
 * 商品列表适配器
 * @author wangshuang
 * 
 */
public class SortGridAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<CategorySort> sorts;
	private String url;
	private BitmapUtils bitmapUtils;
	private BitmapDisplayConfig config;

	public SortGridAdapter(Context context, ArrayList<CategorySort> all_sort) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.sorts=all_sort;
		bitmapUtils=new BitmapUtils(context);
		config = new BitmapDisplayConfig();
	}

	@Override
	public int getCount() {
		return sorts == null ? 0 : sorts.size();
	}

	@Override
	public Object getItem(int position) {
		return sorts == null ? null: sorts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.sort_grid, null);
		}
		TextView text = ViewHolder.get(convertView, R.id.grid_text);
		ImageView image=ViewHolder.get(convertView, R.id.grid_image);
		text.setText(sorts.get(position).getDispNameCn());
		url=ServiceUrlConstants.getImageHost()+sorts.get(position).getPicUrl();

		config.setLoadingDrawable(context.getResources().getDrawable(
				R.drawable.img_default_114));
		config.setLoadFailedDrawable(context.getResources().getDrawable(
				R.drawable.img_default_114));
		bitmapUtils.display(image,url, config);
		return convertView;

	}

}
