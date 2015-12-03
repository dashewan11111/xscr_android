package com.adult.android.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.adult.android.R;
import com.adult.android.utils.ImageUtil;

public class BasePopUpWindow extends PopupWindow {

	public LinearLayout llytShadow;

	public BasePopUpWindow(Context context, LinearLayout llytShadow) {
		this.llytShadow = llytShadow;
		setFocusable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new ColorDrawable(0));
		// 窗口进入和退出的效果
		setAnimationStyle(R.style.win_ani_top_bottom);
		setWidth(ImageUtil.getScreenWidth(context));
		setHeight(ImageUtil.getScreenHeight(context) * 2 / 3);
		RelativeLayout.LayoutParams transLayoutParams = (RelativeLayout.LayoutParams) llytShadow
				.getLayoutParams();
		transLayoutParams.width = ImageUtil.getScreenWidth(context);
		transLayoutParams.height = ImageUtil.getScreenHeight(context);
		llytShadow.setLayoutParams(transLayoutParams);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		llytShadow.setVisibility(View.GONE);
	}

	public void show(View baseView) {
		llytShadow.setVisibility(View.VISIBLE);
		showAsDropDown(baseView, 0, 0);
	}

}
