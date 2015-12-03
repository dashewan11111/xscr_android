package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.ReplyDto;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

public class ReplyAdapter extends BaseAdapter {

	private Context context;

	private List<ReplyDto> items;

	private BitmapUtils bitmapUtils;

	public ReplyAdapter(Context context, List<ReplyDto> items) {
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
	public ReplyDto getItem(int position) {
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
					R.layout.item_community_reply_list, null);
			holder.masterImage = (ImageView) convertView
					.findViewById(R.id.item_community_reply_master_image);
			holder.masterName = (TextView) convertView
					.findViewById(R.id.item_community_reply_master_name);
			holder.masterLevel = (TextView) convertView
					.findViewById(R.id.item_community_reply_master_level);
			holder.flowNum = (TextView) convertView
					.findViewById(R.id.item_community_reply_master_flow_num);
			holder.creatTime = (TextView) convertView
					.findViewById(R.id.item_community_reply_creat_time);
			holder.replyContent = (TextView) convertView
					.findViewById(R.id.item_community_reply_master_content);
			holder.txtFlowReply1 = (TextView) convertView
					.findViewById(R.id.item_community_reply_flow_reply1);
			holder.txtFlowReply2 = (TextView) convertView
					.findViewById(R.id.item_community_reply_flow_reply2);
			holder.txtFlowReplyMore = (TextView) convertView
					.findViewById(R.id.item_community_reply_flow_more);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ReplyDto reply = (ReplyDto) getItem(position);
		holder.masterName.setText(reply.getReplierName());
		holder.masterLevel.setText(reply.getLevel());
		holder.flowNum.setText(reply.getFloor() + "F");
		holder.creatTime.setText(reply.getReplyTime());
		holder.replyContent.setText(reply.getContent());
		// holder.txtFlowReply1.setText(reply.getFlowReply1());
		// holder.txtFlowReply2.setText(reply.getFlowReply2());
		// holder.txtFlowReplyMore.setText("查看更多" + reply.getFlowReplyCount()
		// + "条");
		// holder.txtFlowReplyMore.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// ToastUtil.showToastShort(context, "更多");
		// }
		// });
		bitmapUtils.display(holder.masterImage,
				ServiceUrlConstants.getImageHost() + reply.getReplierImgUrl());
		return convertView;
	}

	private class ViewHolder {

		private ImageView masterImage;

		private TextView masterName;

		private TextView masterLevel;

		private TextView replyContent;

		private TextView flowNum;

		private TextView creatTime;

		private TextView txtFlowReply1;

		private TextView txtFlowReply2;

		private TextView txtFlowReplyMore;

	}
}
