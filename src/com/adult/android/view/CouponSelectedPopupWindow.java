package com.adult.android.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.adult.android.R;
import com.adult.android.entity.CouponDto;
import com.adult.android.entity.ProductRule;
import com.lidroid.xutils.BitmapUtils;

public class CouponSelectedPopupWindow extends BasePopUpWindow {

	private Context context;

	private List<CouponDto> couponList;

	private LinearLayout llytContainer;

	private BitmapUtils mBitmapUtils;

	private CouponSelectedPopupWindowListener listener;

	public CouponSelectedPopupWindow(Context context, LinearLayout llytShadow,
			List<CouponDto> couponList,
			CouponSelectedPopupWindowListener listener) {
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

	public interface CouponSelectedPopupWindowListener {
		void onPopupWindowDismiss();

		void onCouponSelected(ProductRule rule);
	}
}
