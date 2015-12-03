package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;

public class HistoryAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<String> mData;
	private int size;
	
	public HistoryAdapter(ArrayList<String> showHistoryList, Context c) {
		
		this.mData = showHistoryList;
		this.mContext =c;
		size=mData.size();
		if(size>=10){
			size=10;
		}
	}
	
	public void updateListView(ArrayList<String> showHistoryList) {
		this.mData =showHistoryList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData==null?0:size;
	}

	@Override
	public Object getItem(int position) {
		return mData==null?null:mData.get(position).toString();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView =View.inflate(mContext,R.layout.history_item, null);
		}
		TextView text=ViewHolder.get(convertView,R.id.history_text);
		if(size>0){
			text.setText(mData.get(position).toString());
		}
		return convertView;
	}

}
