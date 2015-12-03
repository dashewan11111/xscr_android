package com.adult.android.view.widget.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.OrderLogisticsMsg;

/**
 * 商品类别适配器
 * 
 * @author Cool
 * 
 */
public class ProgressAdapter extends BaseAdapter {

	private Context context;
	private List<OrderLogisticsMsg> orderMsgs = new ArrayList<OrderLogisticsMsg>();
	private OrderLogisticsMsg orderLogisticsMsg;

	public ProgressAdapter(Context context, List<OrderLogisticsMsg> orderMsgs) {
		this.context = context;
		this.orderMsgs = orderMsgs;
	}

	@Override
	public int getCount() {
		return orderMsgs.size();
	}

	@Override
	public Object getItem(int position) {
		return orderMsgs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		orderLogisticsMsg = orderMsgs.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.order_progress_item,
					null);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView
					.findViewById(R.id.img_progress_status);
			holder.des = (TextView) convertView
					.findViewById(R.id.txt_progress_des);
			holder.time = (TextView) convertView
					.findViewById(R.id.txt_progress_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (orderLogisticsMsg != null) {
			holder.img.setImageResource(R.drawable.time_point_gray);
			holder.des.setText(orderLogisticsMsg.getMsg());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(orderLogisticsMsg.getCreateTime());
			holder.time.setText(date);
			if (position == orderMsgs.size() - 1) {
				holder.img.setImageResource(R.drawable.time_point_purple);
			}
		}

		return convertView;
	}

	static class ViewHolder {
		private ImageView img;
		private TextView des;
		private TextView time;
	}
}
