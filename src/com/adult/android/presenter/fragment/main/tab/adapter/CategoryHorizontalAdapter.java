package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CountryInfo;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
public class CategoryHorizontalAdapter extends BaseAdapter {

	private Context context;
	public ArrayList<CountryInfo> countrys;
	private String image_url;
	private ImageView image;
	private TextView text1;
	private TextView text2;
	private BitmapUtils bitmapUtils;
	private BitmapDisplayConfig config;

	public CategoryHorizontalAdapter(Context context,
			ArrayList<CountryInfo> countrys) {
		this.context = context;
		this.countrys = countrys;
		bitmapUtils = new BitmapUtils(context);
		config = new BitmapDisplayConfig();
	}

	@Override
	public int getCount() {
		return countrys == null ? 0 : countrys.size();
	}

	@Override
	public Object getItem(int position) {
		return countrys == null ? null : countrys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.category_horizontal, null);
		}
		image = (ImageView) convertView.findViewById(R.id.country_horizontal_image);
		text1 = (TextView) convertView.findViewById(R.id.h1_text);
		text2 = (TextView) convertView.findViewById(R.id.h2_text);

		if (countrys.get(position).getNameEn() != null) {
			text1.setText(countrys.get(position).getNameEn());
			text2.setText(countrys.get(position).getName());
			image_url = ServiceUrlConstants.getImageHost()
					+ countrys.get(position).getImg();
			config.setLoadingDrawable(context.getResources().getDrawable(
					R.drawable.img_default_114));
			config.setLoadFailedDrawable(context.getResources().getDrawable(
					R.drawable.img_default_114));
			bitmapUtils.display(image, image_url, config);
		}
		return convertView;
	}

}
