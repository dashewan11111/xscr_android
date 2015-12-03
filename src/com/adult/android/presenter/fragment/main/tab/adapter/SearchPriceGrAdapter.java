package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.PriceInfo;

/**
 * Created by huangchao on 8/10/2015.
 */
public class SearchPriceGrAdapter extends BaseAdapter {

	private Context context;

	private List<PriceInfo> searchList;

	private Map<Integer, PriceInfo> priceMap = null;

	public SearchPriceGrAdapter(Context context, List<PriceInfo> searchList,
			Map<Integer, PriceInfo> priceMap) {
		this.context = context;
		this.searchList = searchList;
		this.priceMap = priceMap;
	}

	@Override
	public int getCount() {

		if (searchList != null && searchList.size() > 0) {
			return searchList.size();
		} else {
			return 0;
		}

	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ccigmall_search_condition_gr_item, null);
		}

		TextView search_price_gr_item_tv = (TextView) convertView
				.findViewById(R.id.search_price_gr_item_tv);
		search_price_gr_item_tv.setText(context.getResources().getString(
				R.string.rmb)
				+ searchList.get(position).getName());

		if (priceMap.get(position) != null) {
			search_price_gr_item_tv.setTextColor(context.getResources()
					.getColor(R.color.main_text_color));
		} else {
			search_price_gr_item_tv.setTextColor(context.getResources()
					.getColor(R.color.black));
		}
		return convertView;
	}
}
