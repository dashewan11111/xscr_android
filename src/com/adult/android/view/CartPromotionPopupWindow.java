package com.adult.android.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CouponDto;
import com.lidroid.xutils.BitmapUtils;

public class CartPromotionPopupWindow extends BasePopUpWindow {

	private Context context;

	private List<CouponDto> couponList;

	private LinearLayout llytContainer;

	private BitmapUtils mBitmapUtils;

	private CartPromotionPopupWindowListener listener;

	public CartPromotionPopupWindow(Context context, LinearLayout llytShadow,
			List<CouponDto> couponList,
			CartPromotionPopupWindowListener listener) {
		super(context, llytShadow);
		this.context = context;
		this.couponList = couponList;
		this.listener = listener;
		mBitmapUtils = new BitmapUtils(context);
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		mBitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
		initPopupWindow();
	}

	private void initPopupWindow() {
		llytContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.pop_cart_promotion_list, null);
		setContentView(llytContainer);
		addItems();
	}

	private void addItems() {
		if (null == couponList) {
			return;
		}
		for (int i = 0; i < couponList.size(); i++) {
			final CouponDto coupon = couponList.get(i);
			if (null == coupon) {
				return;
			}
			View convertView = LayoutInflater.from(context).inflate(
					R.layout.item_coupon_list, null);
			TextView txtValue = (TextView) convertView
					.findViewById(R.id.item_coupon_value);
			TextView txtType = (TextView) convertView
					.findViewById(R.id.item_coupon_type);
			TextView txtDesc = (TextView) convertView
					.findViewById(R.id.item_coupon_desc);
			TextView txtTime = (TextView) convertView
					.findViewById(R.id.item_coupon_time);
			txtValue.setText(coupon.getCoupon_amount());
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
			txtType.setText(type);
			txtDesc.setText("." + coupon.getClaim_limit());
			txtTime.setText("." + sd.format(new Date(coupon.getStartTime()))
					+ "至" + sd.format(new Date(coupon.getEndTime())));
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					listener.onPromotionSelected(coupon);

				}
			});
			llytContainer.addView(convertView);
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		listener.onPopupWindowDismiss();
	}

	public void setSelected(View view) {
		for (int i = 0; i < llytContainer.getChildCount(); i++) {
			View item = llytContainer.getChildAt(i);
			(item.findViewById(R.id.item_round_conner_text))
					.setBackgroundResource(R.drawable.common_round_conner_bg_gray);
		}
		(view.findViewById(R.id.item_round_conner_text))
				.setBackgroundResource(R.drawable.common_round_conner_bg_yellow);
	}

	public interface CartPromotionPopupWindowListener {
		void onPopupWindowDismiss();

		void onPromotionSelected(CouponDto rule);
	}

	@Override
	public void show(View baseView) {
		llytShadow.setVisibility(View.VISIBLE);
		showAtLocation(baseView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
				0);
	}
}
