package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.Comment;
/**
 * 商品类别适配器
 * 
 * @author Cool
 * 
 */
public class CommentAdapter extends BaseAdapter {

	private Context context;
	// private FinalBitmap fb;
	private List<Comment> commentList = new ArrayList<Comment>();
	private Comment comment;

	public CommentAdapter(Context context, List<Comment> commentList) {
		this.context = context;
		this.commentList = commentList;
	}

	@Override
	public int getCount() {
		return commentList.size();
	}

	@Override
	public Object getItem(int position) {
		return commentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		comment = commentList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.comment_list_item,
					null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.txt_user_name);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.imgview_user_icon);
			holder.grade = (ImageView) convertView
					.findViewById(R.id.imgview_user_grade);
			holder.ratingBar = (RatingBar) convertView
					.findViewById(R.id.ratingbar);
			holder.comment = (TextView) convertView
					.findViewById(R.id.txt_comment_context);
			holder.date = (TextView) convertView
					.findViewById(R.id.txt_comment_date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (comment != null) {
			// holder.name.setText(comment.getUserName());
			// holder.price.setText(commentList.get(position).getFprice());
			// holder.fbagweight
			// .setText(commentList.get(position).getFbagweight());
			// holder.fnumber.setText(commentList.get(position).getFnumber());
			// fb.display(holder.icon, products.get(position).getImgsrc());
		}

		return convertView;
	}

	static class ViewHolder {
		private ImageView icon;
		private TextView name;
		private ImageView grade;
		private RatingBar ratingBar;
		private TextView comment;
		private TextView date;
	}
}
