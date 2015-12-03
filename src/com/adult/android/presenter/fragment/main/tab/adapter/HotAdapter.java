package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.SearchHot;
/**
 * 热门搜索适配器*/
public class HotAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<SearchHot> hots;
	private String hot_text;

	public HotAdapter(Context c, ArrayList<SearchHot> hot_items) {
		// TODO Auto-generated constructor stub
		context=c;
		this.hots=hot_items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hots==null?0:hots.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return hots==null?null:hots.get(position);
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
			convertView =View.inflate(context,R.layout.hot_item, null);
		}
		TextView text=ViewHolder.get(convertView,R.id.hot_text);
		hot_text=hots.get(position).getKw();
		if(!TextUtils.isEmpty(hot_text)){
			text.setText(hots.get(position).getKw());
		}
		
		return convertView;
		
	}
	
}
