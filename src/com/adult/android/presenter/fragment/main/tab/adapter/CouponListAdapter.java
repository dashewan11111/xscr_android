package com.adult.android.presenter.fragment.main.tab.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CouponDto;
import com.lidroid.xutils.BitmapUtils;

@SuppressLint("SimpleDateFormat")
public class CouponListAdapter extends BaseAdapter {

	private Context context;

	private List<CouponDto> items;

	private BitmapUtils bitmapUtils;

	public CouponListAdapter(Context context, List<CouponDto> items) {
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
					R.layout.item_coupon_list, null);

			holder.txtValue = (TextView) convertView
					.findViewById(R.id.item_coupon_value);
			holder.txtType = (TextView) convertView
					.findViewById(R.id.item_coupon_type);
			holder.txtDesc = (TextView) convertView
					.findViewById(R.id.item_coupon_desc);
			holder.txtTime = (TextView) convertView
					.findViewById(R.id.item_coupon_time);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CouponDto coupon = (CouponDto) getItem(position);

		holder.txtValue.setText(coupon.getCoupon_amount());
		String type = "";
		switch (Integer.parseInt(coupon.getCoupon_type())) {
		case 0:
			type = "满减券";
			break;
		case 1:
			type = "现金券";
			break;
		case 2:
			type = "***券";
			break;
		default:
			break;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
		holder.txtType.setText(type);
		holder.txtDesc.setText("." + coupon.getClaim_limit());
		holder.txtTime.setText("." + sd.format(new Date(coupon.getStartTime()))
				+ "至" + sd.format(new Date(coupon.getEndTime())));
		return convertView;
	}

	private class ViewHolder {
		private TextView txtValue, txtType, txtDesc, txtTime;
	}
}
