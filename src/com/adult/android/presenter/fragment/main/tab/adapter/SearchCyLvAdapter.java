package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CyidInfo;

/**
 * Created by huangchao on 8/10/2015.
 */
public class SearchCyLvAdapter extends BaseAdapter {

    private Context context;

    private List<CyidInfo> searchList;

    private Map<Integer, CyidInfo> cyidMap = null;

    public SearchCyLvAdapter(Context context, List<CyidInfo> searchList, Map<Integer, CyidInfo> cyidMap){
        this.context = context;
        this.searchList = searchList;
        this.cyidMap = cyidMap;
    }

    @Override
    public int getCount() {

        if(searchList != null && searchList.size() > 0){
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
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.ccigmall_search_condition_lv_item, null);
        }

        RelativeLayout search_lv_pw_item_layout = (RelativeLayout)convertView.findViewById(R.id.search_lv_pw_item_layout);

        TextView search_lv_pw_item_tv = (TextView)convertView.findViewById(R.id.search_lv_pw_item_tv);
        search_lv_pw_item_tv.setText(searchList.get(position).getName());

        if(cyidMap.get(position) != null){
            search_lv_pw_item_layout.setBackgroundColor(context.getResources().getColor(R.color.gray_bg1));
        } else {
            search_lv_pw_item_layout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        return convertView;
    }
}
