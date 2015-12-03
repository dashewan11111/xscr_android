package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CategoryInfo;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.utils.Misc;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
public class CategoryListAdapter extends BaseAdapter {

	private Context context;
	// 国旗图标
	private ImageView flag;
	// 国名的英文
	private TextView country;
	// 国外直供描述
	private TextView detail;
	// 商品名称
	private TextView product_name;
	// 现在的价格
	private TextView price;
	// 国外的价格
	private TextView foreign_price;
	// 国内的价格
	private TextView home_price_detail;
	private List<CategoryInfo> items;
	private BitmapUtils bitmapUtils;
	private ImageView productimg;
	private BitmapDisplayConfig config;
	private ImageView promote;
	private String promotion;
	private CategoryInfo items_position;

	public CategoryListAdapter(Context context, List<CategoryInfo> categorylist) {
		// TODO Auto-generated constructor stub
		this.context = context;
		items = categorylist;
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items == null ? null : items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.category_vertical, null);
		}
		productimg = (ImageView) convertView.findViewById(R.id.product_image);
		flag = (ImageView) convertView.findViewById(R.id.flag);
		promote = (ImageView) convertView.findViewById(R.id.promote);
		country = (TextView) convertView.findViewById(R.id.country);
		detail = (TextView) convertView.findViewById(R.id.detail);
		product_name = (TextView) convertView.findViewById(R.id.product_name);
		price = (TextView) convertView.findViewById(R.id.price);
		foreign_price = (TextView) convertView.findViewById(R.id.foreign_price);
		home_price_detail = (TextView) convertView
				.findViewById(R.id.home_price_detail);
		items_position = items.get(position);
		if (items_position!= null) {
			country.setText(items_position.getCyName());
			detail.setText(items_position.getProStyleDescribe());
			product_name.setText(items_position.getB2cPname());
			price.setText(String.format(
					context.getResources().getString(R.string.renminbi), Misc
							.scale(Double.parseDouble(items_position
									.getUnit_price()), 2)));
			foreign_price
					.setText("("
							+ context.getResources().getString(R.string.about)
							+ Html.fromHtml(items_position.getExchange()) + ")");
			home_price_detail.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			home_price_detail
					.setText(String
							.format(context.getResources().getString(
									R.string.renminbi), Misc.scale(Double
									.parseDouble(items_position
											.getDomestic_price()), 2)));

			promotion = items_position.getPromotion();
			if (promotion != null && promotion.equals("10")) {
				promote.setVisibility(View.VISIBLE);
			}
			bitmapUtils = new BitmapUtils(context);
			config = new BitmapDisplayConfig();
			config.setLoadingDrawable(context.getResources().getDrawable(
					R.drawable.img_default_114));
			config.setLoadFailedDrawable(context.getResources().getDrawable(
					R.drawable.img_default_114));
			bitmapUtils.display(flag, ServiceUrlConstants.getImageHost()
					+ items_position.getCountryImg(), config);
			bitmapUtils.display(productimg, ServiceUrlConstants.getImageHost()
					+ items_position.getImageurl(), config);
		}
		return convertView;
	}
}
