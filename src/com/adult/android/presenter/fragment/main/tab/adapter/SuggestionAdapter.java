package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.SuggestionInfo;
/**
 * 热门搜索适配器*/
public class SuggestionAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<SuggestionInfo> suggestions;
	private String suggest_text;

	public SuggestionAdapter(Context c,
			ArrayList<SuggestionInfo> suggestionList) {
		// TODO Auto-generated constructor stub
		context=c;
		inflater=LayoutInflater.from(context);
		this.suggestions=suggestionList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return suggestions.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
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
			convertView = inflater.inflate(R.layout.suggest_item, null);
		}
		TextView text=ViewHolder.get(convertView,R.id.suggest_text);
		suggest_text=suggestions.get(position).getKeyword();
		if(!TextUtils.isEmpty(suggest_text)){
			text.setText(suggestions.get(position).getKeyword());
		}
		
		return convertView;
		
	}
	
}
