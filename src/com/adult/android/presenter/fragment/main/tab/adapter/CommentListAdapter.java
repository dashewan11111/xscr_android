package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.Comment2;
import com.lidroid.xutils.BitmapUtils;

public class CommentListAdapter extends BaseAdapter {

	private Context context;

	private List<Comment2> items;

	private BitmapUtils bitmapUtils;

	public CommentListAdapter(Context context, List<Comment2> items) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_list, null);
			holder.itemRatingBar = (RatingBar) convertView.findViewById(R.id.item_comment_point);
			holder.txtCommenter = (TextView) convertView.findViewById(R.id.item_commenter);
			holder.txtCommentTime = (TextView) convertView.findViewById(R.id.item_comment_time);
			holder.txtCommentContent = (TextView) convertView.findViewById(R.id.item_comment_content);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Comment2 comment = (Comment2) getItem(position);
		holder.itemRatingBar.setRating(comment.getPoint());
		if (comment.isAnonymous() || null == comment.getCreatorName()) {
			holder.txtCommenter.setText("匿名");
		} else {
			holder.txtCommenter.setText(comment.getCreatorName());
		}

		holder.txtCommentTime.setText(comment.getCreatTime());
		holder.txtCommentContent.setText(comment.getContent());

		// addImages(holder.llytImageContainer, topic.getTopicImageList());

		return convertView;
	}

	private class ViewHolder {

		private RatingBar itemRatingBar;

		private TextView txtCommenter, txtCommentTime, txtCommentContent;

	}
}
