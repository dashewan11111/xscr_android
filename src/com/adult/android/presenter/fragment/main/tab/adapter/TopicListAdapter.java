package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.TopicDTO;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

public class TopicListAdapter extends BaseAdapter {

	private Context context;

	private List<TopicDTO> items;

	private BitmapUtils bitmapUtils;

	public TopicListAdapter(Context context, List<TopicDTO> items) {
		this.context = context;
		this.items = items;
		bitmapUtils = new BitmapUtils(context);
		// bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_248);
		// bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_248);
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
					R.layout.item_topic_list, null);
			holder.imgUserAvator = (ImageView) convertView
					.findViewById(R.id.item_topic_list_user_avator);
			holder.txtUserName = (TextView) convertView
					.findViewById(R.id.item_topic_list_user_name);
			holder.txtTopicTitle = (TextView) convertView
					.findViewById(R.id.item_topic_list_topic_title);
			holder.txtTopicContent = (TextView) convertView
					.findViewById(R.id.item_topic_list_topic_content);
			holder.llytImageContainer = (RelativeLayout) convertView
					.findViewById(R.id.item_topic_list_topic_image_container);
			holder.image1 = (ImageView) convertView
					.findViewById(R.id.item_topic_list_topic_image_1);
			holder.image2 = (ImageView) convertView
					.findViewById(R.id.item_topic_list_topic_image_2);
			holder.image3 = (ImageView) convertView
					.findViewById(R.id.item_topic_list_topic_image_3);
			holder.txtImageCount = (TextView) convertView
					.findViewById(R.id.item_topic_list_img_count);
			holder.txtLatestVisitor = (TextView) convertView
					.findViewById(R.id.item_topic_list_txt_visitor);
			holder.txtLatestVisitTime = (TextView) convertView
					.findViewById(R.id.item_topic_list_txt_time);
			holder.txtVisitCount = (TextView) convertView
					.findViewById(R.id.item_topic_list_txt_visitor_count);
			holder.txtReplyCount = (TextView) convertView
					.findViewById(R.id.item_topic_list_txt_reply_count);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		TopicDTO topic = (TopicDTO) getItem(position);

		holder.txtUserName.setText(topic.getCreaterName());
		holder.txtTopicTitle.setText(topic.getTitle());
		holder.txtTopicContent.setText(topic.getContent());
		holder.txtLatestVisitor.setText(topic.getLatestReplierName());
		holder.txtLatestVisitTime.setText(topic.getLatestReplyTime());
		holder.txtVisitCount.setText(topic.getVisitCount());
		holder.txtReplyCount.setText(topic.getReplyCount());
		holder.txtImageCount.setText(topic.getImgList().size() + "张");
		switch (topic.getImgList().size()) {
		case 0:
			holder.llytImageContainer.setVisibility(View.GONE);
			holder.image1.setVisibility(View.GONE);
			holder.image2.setVisibility(View.GONE);
			holder.image3.setVisibility(View.GONE);
			break;
		case 1:
			holder.llytImageContainer.setVisibility(View.VISIBLE);
			holder.image1.setVisibility(View.VISIBLE);
			holder.image2.setVisibility(View.GONE);
			holder.image3.setVisibility(View.GONE);
			bitmapUtils.display(holder.image1,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(0).getImgUrl());
			break;
		case 2:
			holder.llytImageContainer.setVisibility(View.VISIBLE);
			holder.image1.setVisibility(View.VISIBLE);
			holder.image2.setVisibility(View.VISIBLE);
			holder.image3.setVisibility(View.GONE);
			bitmapUtils.display(holder.image1,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(0).getImgUrl());
			bitmapUtils.display(holder.image2,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(1).getImgUrl());

			break;
		default:
			holder.llytImageContainer.setVisibility(View.VISIBLE);
			bitmapUtils.display(holder.image1,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(0).getImgUrl());
			bitmapUtils.display(holder.image2,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(1).getImgUrl());
			bitmapUtils.display(holder.image3,
					ServiceUrlConstants.getImageHost()
							+ topic.getImgList().get(2).getImgUrl());
			break;
		}
		bitmapUtils.display(holder.imgUserAvator,
				ServiceUrlConstants.getImageHost() + topic.getCreaterImgUrl());

		// addImages(holder.llytImageContainer, topic.getImgList());

		return convertView;
	}

	private class ViewHolder {

		private ImageView imgUserAvator;

		private TextView txtUserName;

		private TextView txtTopicTitle;

		private TextView txtTopicContent;

		private ImageView image1;

		private ImageView image2;

		private ImageView image3;

		private TextView txtImageCount;

		private TextView txtLatestVisitor;

		private TextView txtLatestVisitTime;

		private TextView txtVisitCount;

		private TextView txtReplyCount;

		private RelativeLayout llytImageContainer;

	}
}
