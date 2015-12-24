package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CommunityDTO;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.view.CircleImageView;
import com.lidroid.xutils.BitmapUtils;

public class CommunityListAdapter extends BaseAdapter {

	private Context context;

	private List<CommunityDTO> items;

	private BitmapUtils bitmapUtils;

	public CommunityListAdapter(Context context, List<CommunityDTO> items) {
		this.context = context;
		this.items = items;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_community_list, null);
			holder.img = (CircleImageView) convertView
					.findViewById(R.id.item_community_list_image);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.item_community_list_title);
			holder.txtContent = (TextView) convertView
					.findViewById(R.id.item_community_list_content);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CommunityDTO topic = (CommunityDTO) getItem(position);
		holder.txtTitle.setText(topic.getName());
		holder.txtContent.setText(topic.getDescription());
		bitmapUtils.display(holder.img, ServiceUrlConstants.getImageHost()
				+ topic.getImgUrl());
		return convertView;
	}

	private class ViewHolder {

		private CircleImageView img;

		private TextView txtTitle;

		private TextView txtContent;

	}
}
