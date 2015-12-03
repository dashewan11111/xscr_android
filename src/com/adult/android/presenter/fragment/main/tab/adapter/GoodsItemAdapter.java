package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
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

/**
 * 商品搜索结果列表适配器
 * 
 * @author wangshuang
 * 
 */
public class GoodsItemAdapter extends BaseAdapter {

	private Context context;
	private List<CategoryInfo> items;
	private BitmapUtils bitmapUtils;
	private TextView country;
	private TextView detail_result;
	private BitmapDisplayConfig config;
	private String promotion;
	private CategoryInfo items_position;

	public GoodsItemAdapter(Context context,
			List<CategoryInfo> categorylistItems) {
		this.context = context;
		items = categorylistItems;
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items == null ?null: items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.search_result_item,
					null);
		}
		TextView name = (TextView) ViewHolder.get(convertView,
				R.id.tv_goods_name);
		ImageView image_result = (ImageView) ViewHolder.get(convertView,
				R.id.iv_goods_pic);
		ImageView flag_result = (ImageView) ViewHolder.get(convertView,
				R.id.flag_result);
		ImageView promote = (ImageView) ViewHolder.get(convertView,
				R.id.promotion);

		country = (TextView) ViewHolder.get(convertView, R.id.country_result);
		detail_result = (TextView) ViewHolder.get(convertView,
				R.id.detail_result);
		TextView price = (TextView) ViewHolder.get(convertView,
				R.id.tv_goods_price);
		TextView homeprice = (TextView) ViewHolder.get(convertView,
				R.id.goods_home_price);
		TextView foreignprice = (TextView) ViewHolder.get(convertView,
				R.id.tv_goods_foreign_price);
		items_position=items.get(position);
		if (items_position!= null) {
			country.setText(items_position.getCyName());
			detail_result.setText(items_position.getProStyleDescribe());
			if(items_position.getHighlightedPname()!=null){
				name.setText(Html.fromHtml(items_position.getHighlightedPname()));
			}else{
				name.setText(items_position.getB2cPname());
			}
			price.setText(String.format(context.getResources().getString(R.string.renminbi),
					Misc.scale(Double.parseDouble(items_position.getUnit_price()),2)));
			foreignprice.setText("("+ context.getResources().getString(R.string.about)
					+ Html.fromHtml(items_position.getExchange())+ ")");
			homeprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			homeprice.setText(String.format(context.getResources().getString(R.string.renminbi), 
					Misc.scale(Double.parseDouble(items_position.getDomestic_price()),2)));

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
			bitmapUtils.display(flag_result, ServiceUrlConstants.getImageHost()
					+items_position.getCountryImg(), config);
			bitmapUtils.display(image_result,
					ServiceUrlConstants.getImageHost()
							+items_position.getImageurl(), config);

		}

		return convertView;
	}

}
