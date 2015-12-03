package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.ProductFilter;
public class FilterLisAdapter extends BaseAdapter {

	private Context context;

	private List<ProductFilter> filterList;

	public FilterLisAdapter(Context context, List<ProductFilter> filterList) {
		this.context = context;
		this.filterList = filterList;
	}

	@Override
	public int getCount() {
		return filterList.size();
	}

	@Override
	public Object getItem(int position) {
		return filterList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_product_filter, null);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.item_product_filter_name);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		ProductFilter filter = (ProductFilter) getItem(position);
		holder.txtName.setText(filter.getFilterName());
		return convertView;
	}

	private class Holder {
		TextView txtName;
	}

}
