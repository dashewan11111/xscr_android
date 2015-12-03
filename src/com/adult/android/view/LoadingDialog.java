package com.adult.android.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;

/**
 * @ClassName: LoadingDialog
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月7日 上午11:06:07
 * 
 */
public class LoadingDialog {
	Context context;
	Dialog dlg;
	View view;
	ImageView imageView;
	TextView text;
	boolean isCancelOutside = false;

	public LoadingDialog(Context context) {
		this.context = context;
		dlg = new Dialog(context, R.style.loading_dialog);
		view = LayoutInflater.from(context).inflate(
				R.layout.sample_loading_dialog_layout, null);
		// view.getBackground().setAlpha(100);
		imageView = (ImageView) view.findViewById(R.id.loading_image);
	}

	public LoadingDialog(Context context, String str) {
		dlg = new Dialog(context, R.style.loading_dialog);
		view = LayoutInflater.from(context).inflate(
				R.layout.sample_loading_dialog_layout, null);
		// view.getBackground().setAlpha(100);
		text = (TextView) view.findViewById(R.id.dialog_tv);
		text.setText(str);
		imageView = (ImageView) view.findViewById(R.id.loading_image);

	}

	public LoadingDialog(Context context, int strId) {
		dlg = new Dialog(context, R.style.loading_dialog);
		view = LayoutInflater.from(context).inflate(
				R.layout.sample_loading_dialog_layout, null);
		// view.getBackground().setAlpha(250);
		text = (TextView) view.findViewById(R.id.dialog_tv);
		text.setText(context.getResources().getString(strId));
		imageView = (ImageView) view.findViewById(R.id.loading_image);
	}

	public synchronized void show() {
		if (dlg.isShowing())
			return;
		dlg.setContentView(view);
		dlg.setCanceledOnTouchOutside(isCancelOutside);
		dlg.show();
		Window window = dlg.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setLayout(
				context.getResources().getDimensionPixelSize(
						R.dimen.default_loading_size_width),
				context.getResources().getDimensionPixelSize(
						R.dimen.default_loading_size_height));
		imageView.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.rotating));
	}

	public synchronized void show(Boolean cancelable) {
		if (dlg.isShowing())
			return;
		dlg.setContentView(view);
		dlg.setCanceledOnTouchOutside(isCancelOutside);
		dlg.show();
		dlg.setCancelable(cancelable);
		Window window = dlg.getWindow();
		window.setGravity(Gravity.CENTER);
		window.setLayout(
				context.getResources().getDimensionPixelSize(
						R.dimen.default_loading_size_width),
				context.getResources().getDimensionPixelSize(
						R.dimen.default_loading_size_height));
		imageView.startAnimation(AnimationUtils.loadAnimation(context,
				R.anim.rotating));
	}

	public synchronized void dismiss() {
		if (!dlg.isShowing())
			return;
		imageView.clearAnimation();
		dlg.dismiss();
	}

	public void setCancelOutside(boolean isCancelOutside) {
		this.isCancelOutside = isCancelOutside;
	}

	public synchronized void cancel() {
		if (!dlg.isShowing() || imageView.getAnimation() == null)
			return;
		imageView.clearAnimation();
		dlg.cancel();
	}

	public boolean isShowing() {
		return dlg.isShowing();
	}
}
