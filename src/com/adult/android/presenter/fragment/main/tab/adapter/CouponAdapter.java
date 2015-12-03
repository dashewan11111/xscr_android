package com.adult.android.presenter.fragment.main.tab.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CouponModel;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.presenter.activity.CouponActivity.OnMyUseChangeListener;
/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年3月31日
 * 
 * @descripte
 * 
 *            优惠券 adapter
 */
public class CouponAdapter extends BaseAdapter {
	private List<CouponModel> datas;
	private LayoutInflater inflater;
	private int type;
	private HashMap<Integer, CouponModel> usingMap;
	private int orderId;
	private UserAction action;

	private OnMyUseChangeListener onMyUseChangeListener;

	/**
	 * 
	 * @param datas
	 * @param usingMap2
	 * @param orderId
	 * @param lay
	 * @param type
	 *            查看未使用的为0 查看已使用的为1 查看已过期的为2
	 * @param action
	 * @param onMyUseChangeListener
	 */
	public CouponAdapter(List<CouponModel> datas,
			HashMap<Integer, CouponModel> usingMap2, int orderId,
			LayoutInflater lay, int type, UserAction action,
			OnMyUseChangeListener onMyUseChangeListener, Context context) {
		this.datas = datas;
		this.inflater = lay;
		this.type = type;
		this.usingMap = usingMap2;
		this.orderId = orderId;
		this.action = action;
		this.onMyUseChangeListener = onMyUseChangeListener;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.coupon_item, null);
			holder = new Holder();
			holder.txtNum = (TextView) convertView
					.findViewById(R.id.coupon_item_num);
			holder.txtStarTime = (TextView) convertView
					.findViewById(R.id.coupon_item_startTet);
			holder.txtEndTime = (TextView) convertView
					.findViewById(R.id.coupon_item_endTet);
			holder.txtDesc = (TextView) convertView
					.findViewById(R.id.coupon_item_shuomingTet);
			holder.ImageUsed = (ImageView) convertView
					.findViewById(R.id.coupon_item_userImg);
			holder.ImageUse = (ImageView) convertView
					.findViewById(R.id.coupon_item_img_use);
			holder.imageType = (ImageView) convertView
					.findViewById(R.id.coupon_item_type);
			holder.txtAmount = (TextView) convertView
					.findViewById(R.id.coupon_item_txt_amount);
			holder.txtAmountLimit = (TextView) convertView
					.findViewById(R.id.coupon_item_amount_limit);
			holder.btnUse = (Button) convertView
					.findViewById(R.id.coupon_item_btn_use);
			holder.llytHeader = (LinearLayout) convertView
					.findViewById(R.id.coupon_item_header);
			holder.relytFooter = (RelativeLayout) convertView
					.findViewById(R.id.coupon_item_footer);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		final CouponModel model = datas.get(position);
		// 优惠券类型
		switch (model.getCouponType()) {
		// 优惠券
			case 1 :
				holder.llytHeader
						.setBackgroundResource(R.drawable.bg_coupons_red_top);
				holder.relytFooter
						.setBackgroundResource(R.drawable.bg_coupons_red_footer);
				holder.imageType
						.setImageResource(R.drawable.ic_coupons_preferential_normal);
				break;
			// 现金券
			case 2 :
				holder.llytHeader
						.setBackgroundResource(R.drawable.bg_coupons_blue_top);
				holder.relytFooter
						.setBackgroundResource(R.drawable.bg_coupons_blue_footer);
				holder.imageType
						.setImageResource(R.drawable.ic_coupons_cash_normal);
				break;
			default :
				break;
		}
		switch (type) {
		// 查看未使用的为0
			case 0 :
				holder.ImageUsed.setVisibility(8);
				break;
			// 已使用
			case 1 :
				holder.llytHeader
						.setBackgroundResource(R.drawable.bg_coupons_grey);
				holder.relytFooter
						.setBackgroundResource(R.drawable.bg_coupons_grey_footer);
				holder.ImageUsed.setVisibility(0);
				holder.ImageUsed.setBackgroundResource(R.drawable.ic_seal_used);
				holder.txtEndTime.setTextColor(Color.GRAY);
				switch (model.getCouponType()) {
					case 1 :// 优惠券
						holder.imageType
								.setImageResource(R.drawable.ic_coupons_preferential_failure);
						break;
					case 2 :// 现金券
						holder.imageType
								.setImageResource(R.drawable.ic_coupons_cash_failure);
						break;
					default :
						break;
				}
				break;
			// 过期
			case 2 :
				holder.llytHeader
						.setBackgroundResource(R.drawable.bg_coupons_grey);
				holder.relytFooter
						.setBackgroundResource(R.drawable.bg_coupons_grey_footer);
				holder.ImageUsed.setVisibility(0);
				holder.ImageUsed
						.setBackgroundResource(R.drawable.ic_seal_overdue);
				holder.txtEndTime.setTextColor(Color.GRAY);
				switch (model.getCouponType()) {
					case 1 :// 优惠券
						holder.imageType
								.setImageResource(R.drawable.ic_coupons_preferential_failure);
						break;
					case 2 :// 现金券
						holder.imageType
								.setImageResource(R.drawable.ic_coupons_cash_failure);
						break;
					default :
						break;
				}
				break;
			default :
				break;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 编号
		holder.txtNum.setText("编号：" + model.getCouponstockid());
		// 结束时间
		holder.txtEndTime.setText(sd.format(new Date(model.getEndTime())));
		holder.txtAmountLimit.setText(model.getOrderLimitPrice() + " 元");

		// 使用范围
		String[] arrs = model.getLimitName();
		if (arrs != null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arrs.length; i++) {
				sb = sb.append(arrs[i]);
			}
			holder.txtDesc.setText(sb);
		}
		// 价格
		holder.txtAmount.setText("" + model.getPrice());
		holder.txtStarTime.setText(sd.format(new Date(model.getStartTime())));

		if (null != action) {
			holder.imageType.setVisibility(View.VISIBLE);
			holder.btnUse.setVisibility(View.GONE);
			holder.ImageUse.setVisibility(View.GONE);
		} else {
			holder.imageType.setVisibility(View.GONE);
			if (isUsing(model)) {
				holder.btnUse.setText("取消");
				holder.ImageUse.setImageResource(R.drawable.btn_white_check_on);
				holder.btnUse.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						onMyUseChangeListener.onCancel(orderId);
					}
				});
				holder.ImageUse.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						onMyUseChangeListener.onCancel(orderId);
					}
				});
			} else {
				holder.btnUse.setText("使用");
				holder.ImageUse
						.setImageResource(R.drawable.btn_white_check_off);
				holder.btnUse.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						onMyUseChangeListener.onUse(model);
					}
				});
				holder.ImageUse.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						onMyUseChangeListener.onUse(model);
					}
				});
			}

		}
		return convertView;
	}
	/** 是否在使用 */
	private boolean isUsing(CouponModel couponModel) {
		if (null == usingMap) {
			return false;
		}
		Iterator ite = usingMap.keySet().iterator();
		while (ite.hasNext()) {
			int key = (Integer) ite.next();
			CouponModel value = usingMap.get(key);
			if (value.getCouponstockid() == couponModel.getCouponstockid()
					&& key == orderId) {
				return true;
			}
		}
		return false;
	}

	class Holder {
		LinearLayout llytHeader;
		RelativeLayout relytFooter;
		private TextView txtNum, txtAmount, txtStarTime, txtEndTime, txtDesc,
				txtAmountLimit;
		ImageView ImageUse, ImageUsed, imageType;
		Button btnUse;
	}

	public interface OnUseChangeListener {
		void onUse(CouponModel model);

		void onCancel(int orderId);
	}
}
