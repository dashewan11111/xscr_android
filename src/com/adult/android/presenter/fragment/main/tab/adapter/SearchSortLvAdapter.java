package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.SortInfo;
/**
 * Created by huangchao on 8/10/2015.
 */
public class SearchSortLvAdapter extends BaseAdapter {

	private Context context;

	private List<SortInfo> searchList;

	private Map<Integer, SortInfo> sortMap = null;

	public SearchSortLvAdapter(Context context, List<SortInfo> searchList,
			Map<Integer, SortInfo> sortMap) {
		this.context = context;
		this.searchList = searchList;
		this.sortMap = sortMap;
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
					R.layout.ccigmall_search_condition_lv_item, null);
		}
		RelativeLayout search_lv_pw_item_layout = (RelativeLayout) convertView
				.findViewById(R.id.search_lv_pw_item_layout);

		TextView search_lv_pw_item_tv = (TextView) convertView
				.findViewById(R.id.search_lv_pw_item_tv);
		search_lv_pw_item_tv.setText(searchList.get(position)
				.getSort_condition_content());

		ImageView search_lv_pw_item_arrow = (ImageView) convertView
				.findViewById(R.id.search_lv_pw_item_arrow);

		if (searchList.get(position).getSort_inter_param().equals("4")) {
			search_lv_pw_item_arrow.setVisibility(View.VISIBLE);
			search_lv_pw_item_arrow
					.setImageResource(R.drawable.icon_product_sort_up);
		} else if (searchList.get(position).getSort_inter_param().equals("3")) {
			search_lv_pw_item_arrow.setVisibility(View.VISIBLE);
			search_lv_pw_item_arrow
					.setImageResource(R.drawable.icon_product_sort_down);
		} else {
			search_lv_pw_item_arrow.setVisibility(View.GONE);
		}

		if (sortMap.get(position) != null) {
			search_lv_pw_item_layout.setBackgroundColor(context.getResources()
					.getColor(R.color.gray_bg1));
		} else {
			search_lv_pw_item_layout.setBackgroundColor(context.getResources()
					.getColor(R.color.white));
		}
		return convertView;
	}
}
